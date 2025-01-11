package com.example.pokeviewer.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokeviewer.common.ScreenState
import com.example.pokeviewer.common.StateAwareContent

@Composable
fun PokemonDetailsScreen(uiState: PokemonDetailsUiState, onBack: () -> Unit) {
    StateAwareContent(
        screenState = ScreenState.Success,
        isBackEnabled = true,
        title = uiState.pokemon.name,
        onBack = onBack
    ) {
        Text("Detail screen placeholder")
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailsScreenPreview() {
    PokemonDetailsScreen(uiState = PokemonDetailsUiState()) {

    }
}
