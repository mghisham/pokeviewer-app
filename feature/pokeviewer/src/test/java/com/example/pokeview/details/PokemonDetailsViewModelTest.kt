package com.example.pokeview.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.core.common.ScreenState
import com.example.domain.model.DomainState
import com.example.domain.model.PokemonDetails
import com.example.domain.usecase.GetPokemonDetailsUseCase
import com.example.domain.usecase.GetPokemonIdFromUrlUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonDetailsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()
    private val getPokemonDetailsUseCase = mockk<GetPokemonDetailsUseCase>()
    private val getPokemonIdFromUrlUseCase = mockk<GetPokemonIdFromUrlUseCase>()
    private lateinit var viewModel: PokemonDetailsViewModel
    private val savedStateHandle = SavedStateHandle(
        mapOf(
            "name" to POKEMON_NAME,
            "url" to POKEMON_URL
        )
    )


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `init ViewModel and verify initial pokemon details are updated in uiState`() = runTest {
        val pokemonDetails = PokemonDetails(
            name = POKEMON_NAME,
            height = 4,
            imageUrl = "https://example.com/pikachu.jpg"
        )
        val pokemonDetailsSuccess = DomainState.Success(pokemonDetails)
        coEvery { getPokemonDetailsUseCase.invoke(POKEMON_ID) } returns pokemonDetailsSuccess
        coEvery { getPokemonIdFromUrlUseCase.invoke(POKEMON_URL) } returns POKEMON_ID

        viewModel = PokemonDetailsViewModel(
            savedStateHandle,
            getPokemonIdFromUrlUseCase,
            getPokemonDetailsUseCase
        )

        testDispatcher.scheduler.advanceUntilIdle()
        val actualState = viewModel.uiState.first()

        assert(actualState.screenState is ScreenState.Success)
        assertEquals(actualState.pokemonDetails, pokemonDetails)
        coVerify {
            getPokemonIdFromUrlUseCase.invoke(POKEMON_URL)
            getPokemonDetailsUseCase.invoke(POKEMON_ID)
        }
    }

    @Test
    fun `init ViewModel and verify initial pokemon details are not updated in uiState`() = runTest {
        val errorMessage = "An error occurred"
        val pokemonDetailsError = DomainState.Error<PokemonDetails>(errorMessage)
        coEvery { getPokemonDetailsUseCase(POKEMON_ID) } returns pokemonDetailsError
        coEvery { getPokemonIdFromUrlUseCase(POKEMON_URL) } returns POKEMON_ID

        viewModel = PokemonDetailsViewModel(
            savedStateHandle,
            getPokemonIdFromUrlUseCase,
            getPokemonDetailsUseCase
        )
        testDispatcher.scheduler.advanceUntilIdle()

        val actualState = viewModel.uiState.first()
        val screenState = actualState.screenState
        assert(screenState is ScreenState.Error && screenState.message == errorMessage)
        coVerify {
            getPokemonIdFromUrlUseCase.invoke(POKEMON_URL)
            getPokemonDetailsUseCase.invoke(POKEMON_ID)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private companion object {
        const val POKEMON_NAME = "Pikachu"
        const val POKEMON_ID = "1"
        const val POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/1/"
    }
}
