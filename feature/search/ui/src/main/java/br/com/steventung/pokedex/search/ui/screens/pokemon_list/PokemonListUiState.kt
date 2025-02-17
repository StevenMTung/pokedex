package br.com.steventung.pokedex.search.ui.screens.pokemon_list

import br.com.steventung.pokedex.search.domain.model.PokemonDomain

sealed class PokemonListUiState {
    data class Loading(val loadingProgress: Float = 0f) : PokemonListUiState()
    data class Error(val errorMessage: String = "") : PokemonListUiState()
    data class Success(
        val pokemons: List<PokemonDomain> = emptyList(),
        val searchText: String = ""
    ) : PokemonListUiState()
}