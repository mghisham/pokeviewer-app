package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetails(
    val name: String,
    val height: Int,
    val imageUrl: String?
)
