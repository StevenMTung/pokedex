package br.com.steventung.pokedex.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonStats(
    @SerialName("base_stat")
    val baseStats: Int,
    val stat: Stat
) {
    @Serializable
    data class Stat(
        val name: String
    )
}
