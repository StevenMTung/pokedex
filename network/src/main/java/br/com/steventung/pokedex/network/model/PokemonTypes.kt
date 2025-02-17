package br.com.steventung.pokedex.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypes(
    val type: Type
) {
    @Serializable
    data class Type(
        val name: String
    )
}