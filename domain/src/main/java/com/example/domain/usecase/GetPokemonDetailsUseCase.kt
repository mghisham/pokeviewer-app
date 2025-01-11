package com.example.domain.usecase

import com.example.domain.model.DomainState
import com.example.domain.model.PokemonDetails
import com.example.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailsUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {

    suspend operator fun invoke(id: String): DomainState<PokemonDetails> =
        pokemonRepository.getPokemonDetails(id).fold(
            onSuccess = {
                DomainState.Success(it)
            },
            onFailure = {
                DomainState.Error(it.message.orEmpty())
            }
        )
}
