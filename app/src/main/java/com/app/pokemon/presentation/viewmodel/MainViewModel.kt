package com.app.pokemon.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pokemon.data.model.Pokemon
import com.app.pokemon.data.model.Result
import com.app.pokemon.domain.usecase.FetchPokemonsUseCase
import com.app.pokemon.presentation.state.PokemonUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val fetchPokemonsUseCase: FetchPokemonsUseCase) :
    ViewModel() {

    private val _pokemonUiState = MutableStateFlow<PokemonUiState>(PokemonUiState.Loading)
    val pokemonUiState: StateFlow<PokemonUiState> = _pokemonUiState.asStateFlow()

    fun loadCharacters() {
        viewModelScope.launch {
            when (val pokemons: Result<List<Pokemon>> = fetchPokemonsUseCase()) {
                is Result.Success -> {
                    _pokemonUiState.value = PokemonUiState.Success(pokemons.data)
                }

                is Result.Error -> {
                    _pokemonUiState.value = PokemonUiState.Failure(pokemons.message)
                }
            }
        }
    }
}