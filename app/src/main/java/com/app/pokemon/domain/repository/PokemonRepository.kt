package com.app.pokemon.domain.repository

import com.app.pokemon.data.model.Pokemon
import com.app.pokemon.data.model.Result

interface PokemonRepository {
    suspend fun getCharacters(): Result<List<Pokemon>>
}