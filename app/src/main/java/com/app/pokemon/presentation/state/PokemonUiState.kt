package com.app.pokemon.presentation.state

import com.app.pokemon.data.model.Pokemon

sealed class PokemonUiState {
    object Loading: PokemonUiState()
    data class Success(val pokemons: List<Pokemon>): PokemonUiState()
    data class Failure(val errorMessage: String): PokemonUiState()
}