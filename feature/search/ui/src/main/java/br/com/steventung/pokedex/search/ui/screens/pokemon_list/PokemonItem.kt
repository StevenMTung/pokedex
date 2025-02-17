package br.com.steventung.pokedex.search.ui.screens.pokemon_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.steventung.pokedex.network.model.PokemonAbility
import br.com.steventung.pokedex.network.model.PokemonSprites
import br.com.steventung.pokedex.network.model.PokemonStats
import br.com.steventung.pokedex.search.domain.model.PokemonDomain
import br.com.steventung.pokedex.search.domain.model.Types
import br.com.steventung.pokedex.theme.R.drawable
import br.com.steventung.pokedex.theme.theme.PokemonItemColorDarker
import br.com.steventung.pokedex.theme.theme.PokemonItemColorLight
import coil3.compose.AsyncImage

@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    pokemon: PokemonDomain,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.White)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(20.dp),
                color = Color.DarkGray
            )
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        PokemonItemColorLight,
                        PokemonItemColorDarker
                    )
                )
            )
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(shape = RoundedCornerShape(12.dp)),
                model = pokemon.image.other.frontImage.urlImage,
                contentDescription = "pokemon image",
                placeholder = painterResource(drawable.imagem_padrao),
                error = painterResource(drawable.imagem_padrao)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = PokemonItemColorDarker)
                .offset(y = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black)
                    .height(2.dp)
            )
            Icon(Icons.Filled.AddCircle, contentDescription = null, Modifier.size(14.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent)
                .padding(horizontal = 2.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(4.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = "#${pokemon.id}",
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    fontSize = 12.sp
                )
                Text(
                    text = pokemon.name.replaceFirstChar { it.uppercase() },
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    fontSize = 12.sp
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                pokemon.types.forEach { type ->
                    Icon(
                        painterResource(type.icon),
                        contentDescription = "type icon",
                        modifier = Modifier
                            .size(12.dp)
                            .background(color = type.color, shape = CircleShape),
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonItemPreview() {
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
        PokemonAbility(PokemonAbility.Ability("solar-power"))
    )
    PokemonItem(
        pokemon = PokemonDomain(
            id = 6,
            name = "Charizard",
            weight = 905,
            height = 17,
            image = image,
            types = typeList,
            stats = statList,
            abilities = abilityList
        )
    )
}
