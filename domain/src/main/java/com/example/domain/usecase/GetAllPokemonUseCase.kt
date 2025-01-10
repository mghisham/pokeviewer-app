package com.example.domain.usecase

import com.example.domain.model.DomainState
import com.example.domain.model.Pokemon
import com.example.domain.repository.PokemonRepository
import javax.inject.Inject

class GetAllPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(): DomainState<List<Pokemon>> =
        pokemonRepository.getAllPokemon().fold(
            onSuccess = { DomainState.Success(it) },
            onFailure = { DomainState.Error(it.message.orEmpty()) }
        )
}
