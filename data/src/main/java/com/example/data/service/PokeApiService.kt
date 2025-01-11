package com.example.data.service

import com.example.data.repository.model.PokemonDetailsResponse
import com.example.data.repository.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {

    @GET("/api/v2/pokemon")
    suspend fun getAllPokemon(): Response<PokemonListResponse>

    @GET("/api/v2/pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") id: String): Response<PokemonDetailsResponse>
}
