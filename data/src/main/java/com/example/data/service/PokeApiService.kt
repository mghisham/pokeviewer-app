package com.example.data.service

import com.example.data.repository.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokeApiService {

    @GET("/api/v2/pokemon")
    suspend fun getAllPokemon(): Response<PokemonListResponse>
}