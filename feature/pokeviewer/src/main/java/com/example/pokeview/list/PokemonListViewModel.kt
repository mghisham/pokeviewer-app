package com.example.pokeview.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.common.ScreenState
import com.example.domain.model.DomainState
import com.example.domain.usecase.GetAllPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getAllPokemon: GetAllPokemonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonListUiState())
    val uiState: StateFlow<PokemonListUiState> = _uiState

    init {
        getAllPokemonFromApi()
    }

    private fun getAllPokemonFromApi() {
        _uiState.update {
            it.copy(
                screenState = ScreenState.Loading
            )
        }
        viewModelScope.launch {
            when (val result = getAllPokemon()) {
                is DomainState.Success -> _uiState.update {
                    it.copy(
                        screenState = ScreenState.Success,
                        pokemonList = result.data
                    )
                }

                is DomainState.Error -> _uiState.update {
                    it.copy(
                        screenState = ScreenState.Error(result.message)
                    )
                }
            }
        }
    }
}