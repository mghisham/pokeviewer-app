package com.example.domain.model

sealed interface DomainState<out T> {
    data class Error<out T>(val message: String) : DomainState<T>
    data class Success<out T>(val data: T) : DomainState<T>
}