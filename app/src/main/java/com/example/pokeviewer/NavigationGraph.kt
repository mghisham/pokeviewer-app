package com.example.pokeviewer

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokeviewer.details.PokemonDetailsScreen
import com.example.pokeviewer.details.PokemonDetailsViewModel
import com.example.pokeviewer.list.PokemonListScreen
import com.example.pokeviewer.list.PokemonListViewModel
import kotlinx.serialization.Serializable

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ListScreenRoute) {
        composable<ListScreenRoute> {
            val viewModel: PokemonListViewModel = hiltViewModel()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            PokemonListScreen(
                state = uiState.value
            ) {
                navController.navigate(DetailsScreenRoute(it.name, it.url))
            }
        }
        composable<DetailsScreenRoute> {
            val viewModel: PokemonDetailsViewModel = hiltViewModel()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            PokemonDetailsScreen(uiState.value) {
                navController.popBackStack()
            }
        }
    }
}

@Serializable
data object ListScreenRoute

@Serializable
data class DetailsScreenRoute(val name: String, val url: String)
