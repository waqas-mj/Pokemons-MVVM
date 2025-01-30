package com.app.pokemon.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponseModel(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)

@JsonClass(generateAdapter = true)
data class Pokemon(
    val name: String,
    val url: String
)