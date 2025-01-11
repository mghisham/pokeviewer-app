package com.example.domain.usecase

import com.example.domain.common.empty
import org.junit.Assert.assertEquals
import org.junit.Test

class GetPokemonIdFromUrlUseCaseTest {

    private val getPokemonIdFromUrlUseCase = GetPokemonIdFromUrlUseCase()

    @Test
    fun `should return id when getPokemonIdFromUrlUseCase success`() {

        val url = "https://pokeapi.co/api/v2/pokemon/1/"
        val expectedId = "1"

        val actualId = getPokemonIdFromUrlUseCase.invoke(url)

        assertEquals(expectedId, actualId)
    }

    @Test
    fun `should return empty when getPokemonIdFromUrlUseCase fail`() {
        val url = ""
        val expectedId = String.empty

        val actualId = getPokemonIdFromUrlUseCase.invoke(url)

        assertEquals(expectedId, actualId)
    }
}
