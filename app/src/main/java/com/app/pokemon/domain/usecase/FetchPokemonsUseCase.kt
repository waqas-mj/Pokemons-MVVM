package com.app.pokemon.domain.usecase

import com.app.pokemon.data.model.Pokemon
import com.app.pokemon.data.model.Result
import com.app.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class FetchPokemonsUseCase @Inject constructor(private val repository: PokemonRepository){

    suspend operator fun invoke(): Result<List<Pokemon>> {
        return repository.getCharacters()
    }
}