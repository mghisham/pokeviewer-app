package com.example.pokeviewer.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.Pokemon
import com.example.pokeviewer.common.StateAwareContent

@Composable
fun PokemonListScreen(state: PokemonListUiState, onItemClicked: (Pokemon) -> Unit = {}) {
    StateAwareContent(
        screenState = state.screenState
    ) {
        PokemonList(items = state.pokemonList, onItemClicked)
    }
}

@Composable
private fun PokemonList(items: List<Pokemon>, onItemClicked: (Pokemon) -> Unit) {
    LazyColumn {
        items(items) { item ->
            PokemonItem(pokemon = item, onItemClicked = onItemClicked)
        }
    }
}

@Composable
private fun PokemonItem(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    onItemClicked: (Pokemon) -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        ),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .clickable {
                onItemClicked(pokemon)
            }
            .testTag("pokemonItem")
    ) {
        Row {
            Text(
                text = pokemon.name,
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f),
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)
                    .align(Alignment.CenterVertically),
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiaryContainer)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonListScreenPreview() {
    PokemonListScreen(
        state = PokemonListUiState()
    )
}
