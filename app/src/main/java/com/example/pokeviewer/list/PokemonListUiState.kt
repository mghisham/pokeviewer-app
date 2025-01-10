package com.example.pokeviewer.list

import com.example.domain.model.Pokemon
import com.example.pokeviewer.common.ScreenState

data class PokemonListUiState(
    val screenState: ScreenState = ScreenState.Loading,
    val pokemonList: List<Pokemon> = emptyList()
)
