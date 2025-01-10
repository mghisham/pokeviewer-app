package com.example.domain.repository

import com.example.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getAllPokemon(): Result<List<Pokemon>>
}
