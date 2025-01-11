package com.example.pokeview.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.core.common.ScreenState
import com.example.domain.model.Pokemon
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun pokemonListScreenCorrectly() {
        // Sample Data
        val pokemonList = listOf(
            Pokemon(name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/"),
            Pokemon(name = "Pikachu", url = "https://pokeapi.co/api/v2/pokemon/2/"),
        )

        // Set the Compose content for testing
        composeTestRule.setContent {
            PokemonListScreen(
                state = PokemonListUiState(
                    screenState = ScreenState.Success,
                    pokemonList = pokemonList
                )
            )
        }

        // Verify the Pokémon Names are displayed
        pokemonList.forEach {
            composeTestRule.onNodeWithText(it.name).assertIsDisplayed()
        }
    }
}
