package com.example.data.repository

import com.example.data.common.safeApiCall
import com.example.data.repository.model.toPokemonDetails
import com.example.data.repository.model.toPokemonList
import com.example.data.service.PokeApiService
import com.example.domain.model.Pokemon
import com.example.domain.model.PokemonDetails
import com.example.domain.repository.PokemonRepository
import javax.inject.Inject

internal class PokemonRepositoryImpl @Inject constructor(
    private val pokeApiService: PokeApiService
) : PokemonRepository {

    override suspend fun getAllPokemon(): Result<List<Pokemon>> = safeApiCall {
        pokeApiService.getAllPokemon()
    }.mapCatching { it.toPokemonList() }

    override suspend fun getPokemonDetails(id: String): Result<PokemonDetails> = safeApiCall {
        pokeApiService.getPokemonDetails(id)
    }.mapCatching { it.toPokemonDetails() }
}
