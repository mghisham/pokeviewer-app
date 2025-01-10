package com.example.pokeviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokeviewer.list.PokemonListScreen
import com.example.pokeviewer.list.PokemonListViewModel
import com.example.pokeviewer.ui.theme.PokeViewerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeViewerTheme {
                val viewModel: PokemonListViewModel = hiltViewModel()
                val uiState = viewModel.uiState.collectAsStateWithLifecycle()
                PokemonListScreen(uiState.value)
            }
        }
    }
}