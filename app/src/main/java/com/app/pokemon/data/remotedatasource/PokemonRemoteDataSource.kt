package com.app.pokemon.data.remotedatasource

import com.app.pokemon.data.api.PokemonApiService
import com.app.pokemon.data.model.Pokemon
import com.app.pokemon.data.model.PokemonResponseModel
import com.app.pokemon.data.model.Result
import retrofit2.Response
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(private val apiService: PokemonApiService) {

    suspend fun getRemoteData(): Result<List<Pokemon>> {
        return try {

            val response: Response<PokemonResponseModel> = apiService.fetchPokemons()
            return if (response.isSuccessful) {
                Result.Success(response.body()?.results ?: emptyList())
            } else {
                Result.Error(
                    response.code(),
                    response.errorBody()?.string() ?: "Something went wrong"
                )
            }
        } catch (e: Exception) {
            Result.Error(
                null,
                e.localizedMessage ?: "Something went wrong"
            )
        }
    }
}