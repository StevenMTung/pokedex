package br.com.steventung.pokedex.search.ui.screens.details

import br.com.steventung.pokedex.search.domain.model.PokemonDomain

sealed class PokemonDetailsUiState() {
    data object Loading : PokemonDetailsUiState()
    data class Error(val errorMessage: String = "") : PokemonDetailsUiState()
    data class Success(val pokemon: PokemonDomain? = null) : PokemonDetailsUiState()
}