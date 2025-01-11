package com.example.pokeviewer.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.model.PokemonDetails
import com.example.pokeviewer.common.ScreenState
import com.example.pokeviewer.common.StateAwareContent

@Composable
fun PokemonDetailsScreen(uiState: PokemonDetailsUiState, onBack: () -> Unit) {
    StateAwareContent(
        screenState = uiState.screenState,
        isBackEnabled = true,
        title = uiState.title,
        onBack = onBack
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(16.dp)
                .background(
                    shape = MaterialTheme.shapes.large,
                    color = MaterialTheme.colorScheme.surfaceContainer
                ),
        ) {
            AsyncImage(
                contentScale = ContentScale.FillHeight,
                model = uiState.pokemonDetails.imageUrl,
                contentDescription = "${uiState.pokemonDetails.name} Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(16.dp)
                    .testTag("pokemonImage")
            )
            Text(
                uiState.pokemonDetails.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(24.dp)
                    .testTag("pokemonName"),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                "Height : " + uiState.pokemonDetails.height,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp)
                    .testTag("pokemonHeight"),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailsScreenPreview() {
    PokemonDetailsScreen(
        uiState = PokemonDetailsUiState(
            screenState = ScreenState.Success,
            pokemonDetails = PokemonDetails(name = "Pokemon", height = 7, null)
        )
    ) {

    }
}
