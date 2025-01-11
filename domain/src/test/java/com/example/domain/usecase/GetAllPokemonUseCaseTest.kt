package com.example.domain.usecase

import com.example.domain.model.DomainState
import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class GetAllPokemonUseCaseTest {

    private val pokemonRepository = mockk<PokemonRepository>()
    private val getAllPokemonUseCase = GetAllPokemonUseCase(pokemonRepository)


    @Test
    fun `should call invoke on getAllPokemonUseCase to get all pokemon`(): Unit = runBlocking {
        val pokemons = listOf(Pokemon("", ""))
        coEvery { pokemonRepository.getAllPokemon() } returns Result.success(pokemons)

        val result = getAllPokemonUseCase.invoke()

        assertTrue(result is DomainState.Success && result.data == pokemons)
        coVerify { pokemonRepository.getAllPokemon() }
    }

    @Test
    fun `should return error when getAllPokemonUseCase fails`(): Unit = runBlocking {
        val errorMessage = "Failed to fetch pokemons"
        coEvery { pokemonRepository.getAllPokemon() } returns Result.failure(Exception(errorMessage))

        val result = getAllPokemonUseCase.invoke()

        assertTrue(result is DomainState.Error && result.message == errorMessage)
        coVerify { pokemonRepository.getAllPokemon() }
    }
}
