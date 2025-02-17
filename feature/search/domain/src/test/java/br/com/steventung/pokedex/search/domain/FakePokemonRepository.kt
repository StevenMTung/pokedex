package br.com.steventung.pokedex.search.domain

import br.com.steventung.pokedex.common.utils.NetworkResult
import br.com.steventung.pokedex.network.model.PokemonAbility
import br.com.steventung.pokedex.network.model.PokemonResponse
import br.com.steventung.pokedex.network.model.PokemonSprites
import br.com.steventung.pokedex.network.model.PokemonStats
import br.com.steventung.pokedex.network.model.PokemonTypes
import br.com.steventung.pokedex.search.data.PokemonRepository

class FakePokemonRepository : PokemonRepository {

    private val pokemon1 = PokemonResponse(
        id = 1,
        name = "Bulbasaur",
        weight = 69,
        height = 7,
        sprites = PokemonSprites(PokemonSprites.Other(PokemonSprites.Other.Image(urlImage = "bulbasaur_image"))),
        types = listOf(PokemonTypes(PokemonTypes.Type("grass"))),
        stats = listOf(PokemonStats(45, PokemonStats.Stat(name = "hp"))),
        abilities = listOf(
            PokemonAbility(
                PokemonAbility.Ability("bulbasaur_ability")
            )
        )
    )
    private val pokemon2 = PokemonResponse(
        id = 2,
        name = "Ivysaur",
        weight = 130,
        height = 10,
        sprites = PokemonSprites(PokemonSprites.Other(PokemonSprites.Other.Image(urlImage = "ivysaur_image"))),
        types = listOf(
            PokemonTypes(PokemonTypes.Type("grass")),
            PokemonTypes(PokemonTypes.Type("poison"))
        ),
        stats = listOf(PokemonStats(60, PokemonStats.Stat(name = "hp"))),
        abilities = listOf(
            PokemonAbility(
                PokemonAbility.Ability("ivysaur_ability")
            )
        )
    )
    private val pokemon3 = PokemonResponse(
        id = 3,
        name = "Venusaur",
        weight = 1000,
        height = 20,
        sprites = PokemonSprites(PokemonSprites.Other(PokemonSprites.Other.Image(urlImage = "venusaur_image"))),
        types = listOf(
            PokemonTypes(PokemonTypes.Type("grass")),
            PokemonTypes(PokemonTypes.Type("poison"))
        ),
        stats = listOf(PokemonStats(80, PokemonStats.Stat(name = "hp"))),
        abilities = listOf(
            PokemonAbility(
                PokemonAbility.Ability("venusaur_ability")
            )
        )
    )
    private val pokemon4 = PokemonResponse(
        id = 4,
        name = "Charmander",
        weight = 85,
        height = 6,
        sprites = PokemonSprites(PokemonSprites.Other(PokemonSprites.Other.Image(urlImage = "charmander_image"))),
        types = listOf(PokemonTypes(PokemonTypes.Type("fire"))),
        stats = listOf(PokemonStats(39, PokemonStats.Stat(name = "hp"))),
        abilities = listOf(
            PokemonAbility(
                PokemonAbility.Ability("charmander_ability")
            )
        )
    )
    private val pokemon5 = PokemonResponse(
        id = 5,
        name = "Charmeleon",
        weight = 190,
        height = 11,
        sprites = PokemonSprites(PokemonSprites.Other(PokemonSprites.Other.Image(urlImage = "charmeleon_image"))),
        types = listOf(PokemonTypes(PokemonTypes.Type("fire"))),
        stats = listOf(PokemonStats(58, PokemonStats.Stat(name = "hp"))),
        abilities = listOf(
            PokemonAbility(
                PokemonAbility.Ability("charmeleon_ability")
            )
        )
    )
    private val pokemon6 = PokemonResponse(
        id = 6,
        name = "Charizard",
        weight = 905,
        height = 17,
        sprites = PokemonSprites(PokemonSprites.Other(PokemonSprites.Other.Image(urlImage = "charizard_image"))),
        types = listOf(
            PokemonTypes(PokemonTypes.Type("fire")),
            PokemonTypes(PokemonTypes.Type("flying"))
        ),
        stats = listOf(PokemonStats(78, PokemonStats.Stat(name = "hp"))),
        abilities = listOf(
            PokemonAbility(
                PokemonAbility.Ability("charizard_ability")
            )
        )
    )
    private val pokemonList = listOf(
        pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6
    )

    override suspend fun getPokemonById(id: Int): NetworkResult<PokemonResponse> {
        val pokemon = pokemonList.find { it.id == id }
        return pokemon?.let { NetworkResult.Success(it) } ?: NetworkResult.Error(
            body = null,
            exception = Exception()
        )
    }
}