package com.app.pokemon.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponseModel(
    var count: Int,
    var next: String?,
    var previous: String?,
    var results: List<Pokemon>
)

@JsonClass(generateAdapter = true)
data class Pokemon(
    var name: String,
    var url: String
)