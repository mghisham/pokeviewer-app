package com.example.data.di

import com.example.data.repository.PokemonRepositoryImpl
import com.example.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class RepositoryModule {
    @Binds
    abstract fun bindPokemonRepository(
        repository: PokemonRepositoryImpl
    ): PokemonRepository
}
