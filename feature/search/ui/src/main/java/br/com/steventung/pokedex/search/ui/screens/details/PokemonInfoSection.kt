package br.com.steventung.pokedex.search.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.steventung.pokedex.network.model.PokemonAbility
import br.com.steventung.pokedex.network.model.PokemonStats

@Composable
fun PokemonInfoSection(
    modifier: Modifier = Modifier,
    sectionTitle: String = "",
    infoMap: Map<String, Any> = emptyMap()
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(text = sectionTitle, fontSize = 24.sp, color = Color.DarkGray)
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(0.dp)) {
            infoMap.forEach { info ->
                Row {
                    Text(text = "${info.key}: ", color = Color.DarkGray, fontSize = 14.sp)
                    Text(text = info.value.toString(), color = Color.DarkGray, fontSize = 16.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonInfoSectionPreview() {
    val statList = listOf(
        PokemonStats(78, PokemonStats.Stat("hp")),
        PokemonStats(84, PokemonStats.Stat("attack")),
        PokemonStats(78, PokemonStats.Stat("defense")),
        PokemonStats(109, PokemonStats.Stat("special-attack")),
        PokemonStats(85, PokemonStats.Stat("special-defense")),
        PokemonStats(100, PokemonStats.Stat("speed")),
    )
    val statMap = statList.associate { it.stat.name to it.baseStats }

    val height = 1.7F
    val weight = 90.5F
    val dataMap = mapOf("Altura" to "$height m", "Peso" to "$weight kg")

    val abilityList = listOf(
        PokemonAbility(PokemonAbility.Ability("blaze")),
        PokemonAbility(PokemonAbility.Ability("solar-power"))
    )
    val abilityMap = abilityList
        .mapIndexed { index, ability -> (index + 1).toString() to ability.ability.name }
        .toMap()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        PokemonInfoSection(sectionTitle = "Dados", infoMap = dataMap)
        PokemonInfoSection(sectionTitle = "Stats", infoMap = statMap)
        PokemonInfoSection(sectionTitle = "Habilidades", infoMap = abilityMap)
    }
}