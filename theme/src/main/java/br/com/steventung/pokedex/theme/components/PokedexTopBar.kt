package br.com.steventung.pokedex.theme.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import br.com.steventung.pokedex.theme.theme.TopAppBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexTopBar(
    modifier: Modifier = Modifier,
    showBackNavigation: Boolean = false,
    onBackNavigationClick: () -> Unit = {}
) {
    Column(modifier = modifier) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Pokédex",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight(600),
                    color = Color.White
                )
            },
            navigationIcon = {
                if (showBackNavigation) {
                    IconButton(onClick = onBackNavigationClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "arrow back icon",
                            tint = Color.White
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(TopAppBarColor)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PokedexTopBarPreview() {
    PokedexTopBar(showBackNavigation = true)
}