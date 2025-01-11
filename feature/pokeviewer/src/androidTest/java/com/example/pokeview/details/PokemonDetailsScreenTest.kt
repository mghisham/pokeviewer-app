package com.example.pokeview.details

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.core.common.ScreenState
import com.example.domain.model.PokemonDetails
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun pokemonDetailsScreenDisplaysCorrectly() {
        // Sample Data
        val samplePokemonName = "Pikachu"
        val sampleImageUrl =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png"
        val sampleHeight = 4

        // Set the Compose content for testing
        composeTestRule.setContent {
            PokemonDetailsScreen(
                uiState = PokemonDetailsUiState(
                    screenState = ScreenState.Success,
                    pokemonDetails = PokemonDetails(
                        name = samplePokemonName,
                        imageUrl = sampleImageUrl,
                        height = sampleHeight
                    )
                ),
                onBack = {}
            )
        }

        // Verify the Pokémon Name is displayed
        composeTestRule.onNodeWithText(samplePokemonName).assertIsDisplayed()

        // Verify the Pokémon Height is displayed
        composeTestRule.onNodeWithText("Height : $sampleHeight").assertIsDisplayed()

        // Verify the Image is displayed
        composeTestRule.onNodeWithContentDescription("$samplePokemonName Image").assertIsDisplayed()
    }
}
