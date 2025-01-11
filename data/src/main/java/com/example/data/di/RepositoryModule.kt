package com.example.data.di

import com.example.data.repository.PokemonRepositoryImpl
import com.example.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindPokemonRepository(
        repository: PokemonRepositoryImpl
    ): PokemonRepository
}
