package com.example.domain.usecase

import javax.inject.Inject

class GetPokemonIdFromUrlUseCase @Inject constructor() {
    operator fun invoke(url: String): String {
        url.split("/").let {
            val pokemonId = it[it.size - 2]
            return pokemonId
        }
    }
}
