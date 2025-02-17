package br.com.steventung.pokedex.search.domain.model

import androidx.compose.ui.graphics.Color
import br.com.steventung.pokedex.network.model.PokemonAbility
import br.com.steventung.pokedex.network.model.PokemonResponse
import br.com.steventung.pokedex.network.model.PokemonSprites
import br.com.steventung.pokedex.network.model.PokemonStats
import br.com.steventung.pokedex.theme.theme.BugColor
import br.com.steventung.pokedex.theme.theme.DarkColor
import br.com.steventung.pokedex.theme.theme.DragonColor
import br.com.steventung.pokedex.theme.theme.ElectricColor
import br.com.steventung.pokedex.theme.theme.FairyColor
import br.com.steventung.pokedex.theme.theme.FightingColor
import br.com.steventung.pokedex.theme.theme.FireColor
import br.com.steventung.pokedex.theme.theme.FlyingColor
import br.com.steventung.pokedex.theme.theme.GhostColor
import br.com.steventung.pokedex.theme.theme.GrassColor
import br.com.steventung.pokedex.theme.theme.GroundColor
import br.com.steventung.pokedex.theme.theme.IceColor
import br.com.steventung.pokedex.theme.theme.NormalColor
import br.com.steventung.pokedex.theme.theme.PoisonColor
import br.com.steventung.pokedex.theme.theme.PsychicColor
import br.com.steventung.pokedex.theme.R.drawable
import br.com.steventung.pokedex.theme.theme.RockColor
import br.com.steventung.pokedex.theme.theme.SteelColor
import br.com.steventung.pokedex.theme.theme.StellarColor
import br.com.steventung.pokedex.theme.theme.UnknownColor
import br.com.steventung.pokedex.theme.theme.WaterColor

data class PokemonDomain(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val image: PokemonSprites,
    val types: List<Types>,
    val stats: List<PokemonStats>,
    val abilities: List<PokemonAbility>
)

fun PokemonResponse.toPokemonDomain(): PokemonDomain {
    val pokemonTypes = types.map { pokemonType ->
        when(pokemonType.type.name) {
            "grass" -> Types.Grass
            "poison" -> Types.Poison
            "fire" -> Types.Fire
            "flying" -> Types.Flying
            "water" -> Types.Water
            "bug" -> Types.Bug
            "normal" -> Types.Normal
            "electric" -> Types.Electric
            "ground" -> Types.Ground
            "fairy" -> Types.Fairy
            "fighting" -> Types.Fighting
            "psychic" -> Types.Psychic
            "rock" -> Types.Rock
            "steel" -> Types.Steel
            "ice" -> Types.Ice
            "ghost" -> Types.Ghost
            "dragon" -> Types.Dragon
            "stellar" -> Types.Stellar
            "dark" -> Types.Dark
            else -> Types.Unknown
        }
    }
    return PokemonDomain(
        id = id,
        name = name,
        weight = weight,
        height = height,
        image = sprites,
        types = pokemonTypes,
        stats = stats,
        abilities = abilities
    )
}

sealed class Types(val name: String, val color: Color, val icon: Int) {
    data object Grass : Types("Grass", GrassColor, drawable.grass_icon)
    data object Poison : Types("Poison", PoisonColor, drawable.poison_icon)
    data object Fire : Types("Fire", FireColor, drawable.fire_icon)
    data object Flying : Types("Flying", FlyingColor, drawable.flying_icon)
    data object Water : Types("Water", WaterColor, drawable.water_icon)
    data object Bug : Types("Bug", BugColor, drawable.bug_icon)
    data object Normal : Types("Normal", NormalColor, drawable.normal_icon)
    data object Electric : Types("Electric", ElectricColor, drawable.electric_icon)
    data object Ground : Types("Ground", GroundColor, drawable.ground_icon)
    data object Fairy : Types("Fairy", FairyColor, drawable.fairy_icon)
    data object Fighting : Types("Fighting", FightingColor, drawable.fighting_icon)
    data object Psychic : Types("Psychic", PsychicColor, drawable.psychic_icon)
    data object Rock : Types("Rock", RockColor, drawable.rock_icon)
    data object Steel : Types("Steel", SteelColor, drawable.steel_icon)
    data object Ice : Types("Ice", IceColor, drawable.ice_icon)
    data object Ghost : Types("Ghost", GhostColor, drawable.ghost_icon)
    data object Dragon : Types("Dragon", DragonColor, drawable.dragon_icon)
    data object Stellar : Types("Stellar", StellarColor, drawable.stellar_icon)
    data object Dark : Types("Dark", DarkColor, drawable.dark_icon)
    data object Unknown : Types("Unknown", UnknownColor, drawable.none)
}