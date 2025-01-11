package com.example.data.repository.model

import com.example.domain.model.PokemonDetails
import com.google.gson.annotations.SerializedName

data class PokemonDetailsResponse(
    val name: String,
    val height: Int,
    val sprites: Sprites
)

data class Sprites(
    @SerializedName("back_default")
    val imageUrl: String?
)

fun PokemonDetailsResponse.toPokemonDetails(): PokemonDetails {
    return PokemonDetails(
        name = name,
        height = height,
        imageUrl = sprites.imageUrl
    )
}
