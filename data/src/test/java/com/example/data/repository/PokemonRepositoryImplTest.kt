package com.example.data.repository

import com.example.data.repository.model.PokemonItem
import com.example.data.repository.model.PokemonListResponse
import com.example.data.service.PokeApiService
import com.example.domain.model.Pokemon
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Response


class PokemonRepositoryImplTest {
    private val pokeApiService = mockk<PokeApiService>()
    private val pokemonRepository = PokemonRepositoryImpl(pokeApiService)

    @Test
    fun `getPokemonList should return a list of Pokemon`(): Unit = runBlocking {
        val pokemonListResponse = PokemonListResponse(
            count = 1, next = null, previous = null, results = listOf(
                PokemonItem("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/")
            )
        )
        val expectedResult = listOf(Pokemon("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"))
        val response = Response.success(pokemonListResponse)
        coEvery { pokeApiService.getAllPokemon() } returns response

        val result = pokemonRepository.getAllPokemon()

        assertTrue(result.isSuccess)
        assert(result.getOrNull() == expectedResult)
    }

    @Test
    fun `getPokemonList should return an error when API call fails`(): Unit = runBlocking {
        val mockk = mockk<ResponseBody> {
            coEvery { string() } returns "Error"
            coEvery { contentType() } returns null
            coEvery { contentLength() } returns 0
        }
        val response = Response.error<PokemonListResponse>(500, mockk)
        coEvery { pokeApiService.getAllPokemon() } returns response

        val result = pokemonRepository.getAllPokemon()

        assertTrue(result.isFailure)
    }
}
