package br.com.steventung.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.steventung.pokedex.common.navigation.AppDestination
import br.com.steventung.pokedex.search.ui.navigation.homeGraph

@Composable
fun PokedexNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = AppDestination.HomeGraph().route
    ) {
        homeGraph(
            onNavigateToPokemonDetails = { id ->
                navHostController.navigate("${AppDestination.PokemonDetailsGraph().route}/$id")
            },
            onBackNavigation = { navHostController.navigateUp() }
        )
    }
}