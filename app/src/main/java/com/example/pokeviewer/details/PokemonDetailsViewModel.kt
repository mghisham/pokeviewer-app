package com.example.pokeviewer.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    savedState: SavedStateHandle,
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(PokemonDetailsUiState(pokemon = savedState.getPokemon()))
    val uiState: StateFlow<PokemonDetailsUiState> = _uiState
}
