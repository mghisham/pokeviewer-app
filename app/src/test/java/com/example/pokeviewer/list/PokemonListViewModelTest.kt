package com.example.pokeviewer.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.model.DomainState
import com.example.domain.model.Pokemon
import com.example.domain.usecase.GetAllPokemonUseCase
import com.example.pokeviewer.common.ScreenState
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
class PokemonListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()
    private val getAllPokemonUseCase = mockk<GetAllPokemonUseCase>()
    private lateinit var viewModel: PokemonListViewModel


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `init ViewModel and verify initial getPokemonList is updated in uiState`() = runTest {
        val pokemonList = listOf(
            Pokemon("Pikachu", "https://example.com/pikachu.jpg"),
            Pokemon("Bulbasaur", "https://example.com/bulbasaur.jpg")
        )
        coEvery { getAllPokemonUseCase() } returns DomainState.Success(pokemonList)
        viewModel = PokemonListViewModel(getAllPokemonUseCase)

        testDispatcher.scheduler.advanceUntilIdle()

        val actualState = viewModel.uiState.first()

        assert(actualState.screenState is ScreenState.Success)
        assertEquals(actualState.pokemonList, pokemonList)
        coVerify { getAllPokemonUseCase() }
    }

    @Test
    fun `verify getPokemonList is updated in uiState on error`() = runTest {
        val givenErrorMessage = "An error occurred"
        coEvery { getAllPokemonUseCase() } returns DomainState.Error(givenErrorMessage)

        viewModel = PokemonListViewModel(getAllPokemonUseCase)

        testDispatcher.scheduler.advanceUntilIdle()

        val actualState = viewModel.uiState.first()
        val screenState = actualState.screenState
        assert(screenState is ScreenState.Error && screenState.message == givenErrorMessage)
        assert(actualState.pokemonList.isEmpty())
        coVerify { getAllPokemonUseCase() }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
