package br.com.steventung.pokedex.search.ui.samples

import br.com.steventung.pokedex.network.model.PokemonAbility
import br.com.steventung.pokedex.network.model.PokemonSprites
import br.com.steventung.pokedex.network.model.PokemonStats
import br.com.steventung.pokedex.search.domain.model.PokemonDomain
import br.com.steventung.pokedex.search.domain.model.Types

val image = PokemonSprites(
    PokemonSprites.Other(
        PokemonSprites.Other.Image(
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/6.png"
        )
    )
)
val typeList = listOf(Types.Fire, Types.Flying)
val statList = listOf(
    PokemonStats(78, PokemonStats.Stat("hp")),
    PokemonStats(84, PokemonStats.Stat("attack")),
    PokemonStats(78, PokemonStats.Stat("defense")),
    PokemonStats(109, PokemonStats.Stat("special-attack")),
    PokemonStats(85, PokemonStats.Stat("special-defense")),
    PokemonStats(100, PokemonStats.Stat("speed")),
)
val abilityList = listOf(
    PokemonAbility(PokemonAbility.Ability("blaze")),
    PokemonAbility(
        PokemonAbility.Ability(("solar-power"))
    )
)

val pokemonSample = PokemonDomain(
    id = 6,
    name = "Charizard",
    weight = 905,
    height = 17,
    image = image,
    types = typeList,
    stats = statList,
    abilities = abilityList
)

val pokemonListSample =
    listOf(
    pokemonSample, pokemonSample, pokemonSample,
    pokemonSample, pokemonSample, pokemonSample)