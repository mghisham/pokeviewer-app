package com.example.core.common

sealed class ScreenState {
    data class Error(val message: String) : ScreenState()
    data object Loading : ScreenState()
    data object Success : ScreenState()
}
