package com.app.pokemon.data.remotedatasource

import com.app.pokemon.data.api.PokemonApiService
import com.app.pokemon.data.model.Pokemon
import com.app.pokemon.data.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(private val apiService: PokemonApiService) {

    fun getRemoteData(): Flow<Result<List<Pokemon>>> = flow {
        try {
            val response = apiService.fetchPokemons()
            if (response.isSuccessful) {
                emit(Result.Success(response.body()?.results ?: emptyList()))
            } else {
                emit(
                    Result.Error(
                        response.code(),
                        response.errorBody()?.string()?.takeIf { it.isNotEmpty() }
                            ?: "Something went wrong"
                    )
                )
            }
        } catch (e: Exception) {
            emit(
                Result.Error(
                    null,
                    e.localizedMessage ?: "Something went wrong"
                )
            )
        }
    }
}