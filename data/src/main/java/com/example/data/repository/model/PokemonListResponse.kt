package com.example.data.repository.model

import com.example.domain.model.Pokemon

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonItem>
)

data class PokemonItem(
    val name: String,
    val url: String
)

fun PokemonListResponse.toPokemonList(): List<Pokemon> = results.map {
    Pokemon(name = it.name, id = it.url)
}