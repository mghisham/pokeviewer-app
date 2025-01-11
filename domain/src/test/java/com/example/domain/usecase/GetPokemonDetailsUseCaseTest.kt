package com.example.domain.usecase

import com.example.domain.model.DomainState
import com.example.domain.model.PokemonDetails
import com.example.domain.repository.PokemonRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetPokemonDetailsUseCaseTest {
    private val pokemonRepository = mockk<PokemonRepository>()
    private val pokemonDetailsUseCase = GetPokemonDetailsUseCase(pokemonRepository)

    @Test
    fun `should return Pokemon details when getAllPokemonUseCase success`(): Unit = runBlocking {
        val pokemonId = "1"
        val pokemonDetails = PokemonDetails(
            name = "Pikachu",
            height = 4,
            imageUrl = "https://example.com/pikachu.jpg"
        )
        val mockk = Result.success(pokemonDetails)
        coEvery { pokemonRepository.getPokemonDetails(pokemonId) } returns mockk

        val result = pokemonDetailsUseCase.invoke(pokemonId)

        assert(result is DomainState.Success && result.data == pokemonDetails)
        coVerify { pokemonRepository.getPokemonDetails(pokemonId) }
    }

    @Test
    fun `should return error when getAllPokemonUseCase fails`(): Unit = runBlocking {
        val pokemonId = "1"
        val errorMessage = "Failed to fetch Pokemon details"
        val mockk = Result.failure<PokemonDetails>(Exception(errorMessage))
        coEvery { pokemonRepository.getPokemonDetails(pokemonId) } returns mockk

        val result = pokemonDetailsUseCase.invoke(pokemonId)

        assert(result is DomainState.Error && result.message == errorMessage)
        coVerify { pokemonRepository.getPokemonDetails(pokemonId) }
    }
}