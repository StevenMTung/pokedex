package br.com.steventung.pokedex.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    @SerialName("sprites")
    val sprites: PokemonSprites,
    @SerialName("types")
    val types: List<PokemonTypes>,
    @SerialName("stats")
    val stats: List<PokemonStats>,
    @SerialName("abilities")
    val abilities: List<PokemonAbility>
)
