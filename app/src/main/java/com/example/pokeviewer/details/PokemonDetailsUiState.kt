package com.example.pokeviewer.details

import com.example.domain.common.empty
import com.example.domain.model.Pokemon
import com.example.pokeviewer.common.ScreenState

data class PokemonDetailsUiState(
    val screenState: ScreenState = ScreenState.Loading,
    val pokemon: Pokemon = Pokemon(String.empty, String.empty)
)
