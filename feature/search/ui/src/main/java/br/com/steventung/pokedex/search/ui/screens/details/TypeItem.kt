package br.com.steventung.pokedex.search.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.steventung.pokedex.search.domain.model.Types

@Composable
fun TypeItem(
    modifier: Modifier = Modifier,
    pokemonType: Types
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color.DarkGray)
            .width(110.dp)
            .height(intrinsicSize = IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .background(color = pokemonType.color)
                .align(Alignment.CenterVertically)
                .padding(2.dp)
                .fillMaxHeight()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(pokemonType.icon),
                contentDescription = "typeIcon",
                tint = Color.White
            )
        }
        Text(
            text = pokemonType.name,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(top = 2.dp, bottom = 2.dp, start = 4.dp, end = 8.dp)
                .fillMaxHeight()
                .fillMaxWidth()
                .weight(2f),
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showSystemUi = true)
@Composable
private fun TypeItemPreview() {
    FlowRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TypeItem(pokemonType = Types.Fire)
        TypeItem(pokemonType = Types.Bug)
        TypeItem(pokemonType = Types.Ice)
        TypeItem(pokemonType = Types.Dragon)
        TypeItem(pokemonType = Types.Rock)
        TypeItem(pokemonType = Types.Grass)
        TypeItem(pokemonType = Types.Ghost)
        TypeItem(pokemonType = Types.Steel)
        TypeItem(pokemonType = Types.Psychic)
        TypeItem(pokemonType = Types.Fighting)
        TypeItem(pokemonType = Types.Fairy)
        TypeItem(pokemonType = Types.Ground)
        TypeItem(pokemonType = Types.Electric)
        TypeItem(pokemonType = Types.Normal)
        TypeItem(pokemonType = Types.Water)
        TypeItem(pokemonType = Types.Flying)
        TypeItem(pokemonType = Types.Poison)
        TypeItem(pokemonType = Types.Unknown)
    }
}