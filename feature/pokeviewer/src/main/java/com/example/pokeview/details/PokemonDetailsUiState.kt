package com.example.pokeview.details

import com.example.core.common.empty
import com.example.domain.model.PokemonDetails
import com.example.core.common.ScreenState

data class PokemonDetailsUiState(
    val screenState: ScreenState = ScreenState.Loading,
    val title: String = String.empty,
    val url: String = String.empty,
    val pokemonDetails: PokemonDetails = PokemonDetails(String.empty, 0, String.empty)
)
