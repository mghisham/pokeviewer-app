package com.example.pokeviewer.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokeviewer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateAwareContent(
    modifier: Modifier = Modifier,
    title: String? = null,
    isBackEnabled: Boolean = false,
    onBack: () -> Unit = {},
    screenState: ScreenState,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    if (isBackEnabled) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.clickable {
                                onBack()
                            }
                        )
                    }
                },
                title = {
                    Text(title ?: stringResource(R.string.app_name))
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            when (screenState) {
                is ScreenState.Error -> Text(
                    text = screenState.message,
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.error)
                        .padding(16.dp)
                        .align(Alignment.TopCenter),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onError
                )

                ScreenState.Loading -> CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(48.dp)
                        .align(Alignment.Center)
                )

                ScreenState.Success -> content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StateAwareContentPreview_Success() {
    StateAwareContent(screenState = ScreenState.Success) {
        Text(text = "Content")
    }
}

@Preview(showBackground = true)
@Composable
fun StateAwareContentPreview_Error() {
    StateAwareContent(screenState = ScreenState.Error("Error Message")) {
        Text(text = "Content")
    }
}

@Preview(showBackground = true)
@Composable
fun StateAwareContentPreview_Loading() {
    StateAwareContent(screenState = ScreenState.Loading, isBackEnabled = true) {
        Text(text = "Content")
    }
}
