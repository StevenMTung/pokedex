package br.com.steventung.pokedex.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonAbility(
    @SerialName("ability")
    val ability: Ability
) {
    @Serializable
    data class Ability(
        val name: String
    )
}
