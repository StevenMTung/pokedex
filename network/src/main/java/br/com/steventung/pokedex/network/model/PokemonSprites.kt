package br.com.steventung.pokedex.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSprites(
    @SerialName("other")
    val other: Other
) {
    @Serializable
    data class Other(
        @SerialName("official-artwork")
        val frontImage: Image
    ) {
        @Serializable
        data class Image(
            @SerialName("front_default")
            val urlImage: String
        )
    }
}
