package br.com.steventung.pokedex.common.navigation

const val pokemonIdArgument = "pokemonId"

sealed class AppDestination() {
    data class HomeGraph(val route: String = "homeGraphRoute") : AppDestination()
    data class PokemonListGraph(val route: String = "pokemonListGraphRoute") : AppDestination()
    data class PokemonDetailsGraph(
        val route: String = "pokemonDetailsGraphRoute",
        val routeWithArguments: String = "$route/{$pokemonIdArgument}",
    ) : AppDestination()
}