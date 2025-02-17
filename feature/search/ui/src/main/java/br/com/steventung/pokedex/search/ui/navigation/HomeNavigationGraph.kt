package br.com.steventung.pokedex.search.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import br.com.steventung.pokedex.common.navigation.AppDestination
import br.com.steventung.pokedex.common.navigation.pokemonIdArgument
import br.com.steventung.pokedex.search.ui.screens.details.PokemonDetailsScreen
import br.com.steventung.pokedex.search.ui.screens.details.PokemonDetailsViewModel
import br.com.steventung.pokedex.search.ui.screens.pokemon_list.PokemonListScreen
import br.com.steventung.pokedex.search.ui.screens.pokemon_list.PokemonListViewModel

fun NavGraphBuilder.homeGraph(
    onNavigateToPokemonDetails: (Int) -> Unit,
    onBackNavigation: () -> Unit
) {
    navigation(
        startDestination = AppDestination.PokemonListGraph().route,
        route = AppDestination.HomeGraph().route
    ) {
        composable(route = AppDestination.PokemonListGraph().route) {
            val viewModel = hiltViewModel<PokemonListViewModel>()
            val state by viewModel.uiState.collectAsState()
            PokemonListScreen(
                state = state,
                onClickPokemonCard = { id ->
                    onNavigateToPokemonDetails(id)
                },
                onClickRefresh = {
                    viewModel.loadPokemonList()
                },
                onChangedSearchText = { searchText ->
                    viewModel.onEvent(PokemonListViewModel.UiEvent.OnSearchTextChanged(searchText))
                }
            )
        }
        composable(
            route = AppDestination.PokemonDetailsGraph().routeWithArguments,
            arguments = listOf(navArgument(pokemonIdArgument) {
                type = NavType.IntType
            })
        ) {
            val viewModel = hiltViewModel<PokemonDetailsViewModel>()
            val state by viewModel.uiState.collectAsState()
            PokemonDetailsScreen(
                state = state,
                onBackNavigation = onBackNavigation,
                onClickRefresh = { viewModel.loadPokemonInfo() }
            )
        }
    }
}