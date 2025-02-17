package br.com.steventung.pokedex.search.ui.screens.pokemon_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.steventung.pokedex.theme.R.*
import coil3.compose.AsyncImage
import coil3.request.ImageRequest

@Composable
fun LoadingPokeballScreen(
    modifier: Modifier = Modifier,
    loadingState: PokemonListUiState.Loading
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(drawable.pokeball_gif)
                .build(),
            contentDescription = "pokeball gif"
        )
        Text(
            text = "Catching 'em all...",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight(500)
        )
        Spacer(Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { loadingState.loadingProgress }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingScreenPreview() {
    LoadingPokeballScreen(loadingState = PokemonListUiState.Loading())
}