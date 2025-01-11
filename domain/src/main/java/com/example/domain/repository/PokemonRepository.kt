package com.example.domain.repository

import com.example.domain.model.Pokemon
import com.example.domain.model.PokemonDetails

interface PokemonRepository {
    suspend fun getAllPokemon(): Result<List<Pokemon>>
    suspend fun getPokemonDetails(id: String): Result<PokemonDetails>
}
