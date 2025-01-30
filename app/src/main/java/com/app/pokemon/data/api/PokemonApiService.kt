package com.app.pokemon.data.api

import com.app.pokemon.data.model.PokemonResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApiService {
    @GET("pokemon/?offset=0&limit=50")
    suspend fun fetchPokemons() : Response<PokemonResponseModel>
}