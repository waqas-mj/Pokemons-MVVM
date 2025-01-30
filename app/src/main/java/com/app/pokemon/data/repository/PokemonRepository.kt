package com.app.pokemon.data.repository

import com.app.pokemon.data.model.Pokemon
import com.app.pokemon.data.model.Result
import com.app.pokemon.data.remotedatasource.PokemonRemoteDataSource
import com.app.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val pokemonRemoteDataSource: PokemonRemoteDataSource) :
    PokemonRepository {

    override suspend fun getCharacters(): Result<List<Pokemon>> {
        return pokemonRemoteDataSource.getRemoteData()
    }
}