package com.example.pokeview.details

import androidx.lifecycle.SavedStateHandle
import com.example.domain.model.Pokemon

fun SavedStateHandle.getPokemon(): Pokemon {
    val name = get<String>("name").orEmpty()
    val url = get<String>("url").orEmpty()
    return Pokemon(name, url)
}
