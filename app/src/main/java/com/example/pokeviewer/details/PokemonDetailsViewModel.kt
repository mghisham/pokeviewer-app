package com.example.pokeviewer.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DomainState
import com.example.domain.usecase.GetPokemonDetailsUseCase
import com.example.domain.usecase.GetPokemonIdFromUrlUseCase
import com.example.pokeviewer.common.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val getPokemonIdFromUrl: GetPokemonIdFromUrlUseCase,
    private val getPokemonDetails: GetPokemonDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonDetailsUiState())
    val uiState: StateFlow<PokemonDetailsUiState> = _uiState

    init {
        val pokemon = savedState.getPokemon()
        _uiState.update {
            it.copy(
                screenState = ScreenState.Loading,
                title = pokemon.name.uppercase(),
                url = pokemon.url
            )
        }
        fetchPokemonDetails()
    }

    private fun fetchPokemonDetails() {
        val id = getPokemonIdFromUrl(_uiState.value.url)
        _uiState.update {
            it.copy(screenState = ScreenState.Loading)
        }
        viewModelScope.launch {
            when (val result = getPokemonDetails(id)) {
                is DomainState.Error -> _uiState.update {
                    it.copy(screenState = ScreenState.Error(result.message))
                }

                is DomainState.Success -> _uiState.update {
                    it.copy(
                        screenState = ScreenState.Success,
                        pokemonDetails = result.data
                    )
                }
            }
        }
    }
}
