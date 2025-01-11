package com.example.domain.usecase

import javax.inject.Inject

class GetPokemonIdFromUrlUseCase @Inject constructor() {
    operator fun invoke(url: String): String {
        url.split(DELIMITER).let {
            val idIndex = it.size - 2
            val pokemonId = it.getOrNull(idIndex)
            return pokemonId.orEmpty()
        }
    }

    private companion object {
        const val DELIMITER = "/"
    }
}
