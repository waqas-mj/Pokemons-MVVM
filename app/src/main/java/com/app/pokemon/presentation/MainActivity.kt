package com.app.pokemon.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.app.pokemon.data.model.Pokemon
import com.app.pokemon.presentation.state.PokemonUiState
import com.app.pokemon.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Content(viewModel)
        }
        lifecycleScope.launch {
            viewModel.loadCharacters()
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    private fun Content(
        viewModel: MainViewModel
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            val state = viewModel.pokemonUiState.collectAsState()

            when (state.value) {
                is PokemonUiState.Loading -> ShowLoader()
                is PokemonUiState.Success -> ShowData((state.value as PokemonUiState.Success).pokemons)
                is PokemonUiState.Failure -> showError(
                    this, (state.value as PokemonUiState.Failure).errorMessage
                )
            }
        }
    }
}

private fun showError(context: Context, errorMessage: String) {
    AlertDialog
        .Builder(context)
        .setTitle("Error")
        .setMessage(errorMessage)
        .setPositiveButton("Ok") { _, _ -> }.show()
}

@Composable
private fun ShowData(pokemons: List<Pokemon>) {
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                Modifier.verticalScroll(rememberScrollState())
            ) {
                pokemons.forEach {
                    Text(
                        text = it.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ShowLoader() {
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}
