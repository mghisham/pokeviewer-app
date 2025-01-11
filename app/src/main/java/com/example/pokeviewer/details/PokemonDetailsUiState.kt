package com.example.pokeviewer.details

import com.example.domain.common.empty
import com.example.domain.model.PokemonDetails
import com.example.pokeviewer.common.ScreenState

data class PokemonDetailsUiState(
    val screenState: ScreenState = ScreenState.Loading,
    val title: String = String.empty,
    val url: String = String.empty,
    val pokemonDetails: PokemonDetails = PokemonDetails(String.empty, 0, String.empty)
)
