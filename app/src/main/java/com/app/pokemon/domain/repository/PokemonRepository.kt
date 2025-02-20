package com.app.pokemon.domain.repository

import com.app.pokemon.data.model.Pokemon
import com.app.pokemon.data.model.Result
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getCharacters(): Flow<Result<List<Pokemon>>>
}