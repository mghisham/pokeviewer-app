package com.example.pokeview.list

import com.example.domain.model.Pokemon
import com.example.core.common.ScreenState

data class PokemonListUiState(
    val screenState: ScreenState = ScreenState.Loading,
    val pokemonList: List<Pokemon> = emptyList()
)
