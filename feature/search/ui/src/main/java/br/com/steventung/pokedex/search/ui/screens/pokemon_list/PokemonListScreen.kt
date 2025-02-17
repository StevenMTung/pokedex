package br.com.steventung.pokedex.search.ui.screens.pokemon_list

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.steventung.pokedex.search.ui.samples.pokemonListSample
import br.com.steventung.pokedex.theme.components.ErrorScreen
import br.com.steventung.pokedex.theme.components.PokedexTopBar

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    state: PokemonListUiState,
    onClickPokemonCard: (Int) -> Unit = {},
    onClickRefresh: () -> Unit = {},
    onChangedSearchText: (String) -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        containerColor = Color.LightGray,
        topBar = {
            PokedexTopBar()
        }
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            when (state) {
                is PokemonListUiState.Loading -> LoadingPokeballScreen(loadingState = state)
                is PokemonListUiState.Error -> ErrorScreen(
                    errorMessage = state.errorMessage,
                    onClickRefresh = onClickRefresh
                )

                is PokemonListUiState.Success -> {
                    val focusManager = LocalFocusManager.current
                    Column(
                        modifier = Modifier
                            .pointerInput(Unit) {
                                detectTapGestures { focusManager.clearFocus() }
                            }
                    ) {
                        TextField(
                            value = state.searchText,
                            onValueChange = { onChangedSearchText(it) },
                            modifier = Modifier.fillMaxWidth(),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "search icon"
                                )
                            },
                            label = { Text(text = "Pesquisar") },
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            )
                        )
                        Box {
                            LazyVerticalGrid(
                                modifier = Modifier.fillMaxSize(),
                                columns = GridCells.Fixed(3),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                contentPadding = PaddingValues(16.dp)
                            ) {
                                items(state.pokemons) { pokemon ->
                                    PokemonItem(
                                        pokemon = pokemon,
                                        onClick = { onClickPokemonCard(pokemon.id) }
                                    )
                                }
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                Color.LightGray,
                                                Color.Transparent
                                            )
                                        )
                                    )
                            )

                        }
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PokemonListScreenPreview() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.LightGray) {
        PokemonListScreen(
            state = PokemonListUiState.Success(
                pokemons = pokemonListSample
            )
        )
    }
}