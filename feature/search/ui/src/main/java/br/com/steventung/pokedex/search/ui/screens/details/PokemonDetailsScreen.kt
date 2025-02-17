package br.com.steventung.pokedex.search.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.steventung.pokedex.search.ui.samples.pokemonSample
import br.com.steventung.pokedex.theme.R.drawable
import br.com.steventung.pokedex.theme.components.ErrorScreen
import br.com.steventung.pokedex.theme.components.LoadingScreen
import br.com.steventung.pokedex.theme.components.PokedexTopBar
import coil3.compose.AsyncImage

@Composable
fun PokemonDetailsScreen(
    modifier: Modifier = Modifier,
    state: PokemonDetailsUiState,
    onBackNavigation: () -> Unit = {},
    onClickRefresh: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            PokedexTopBar(
                showBackNavigation = true,
                onBackNavigationClick = onBackNavigation
            )
        },
        containerColor = Color.LightGray
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            when (state) {
                is PokemonDetailsUiState.Loading -> LoadingScreen()
                is PokemonDetailsUiState.Error -> ErrorScreen(
                    errorMessage = state.errorMessage,
                    onClickRefresh = onClickRefresh
                )

                is PokemonDetailsUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "#${state.pokemon?.id} ${state.pokemon?.name?.replaceFirstChar { it.uppercase() }}",
                                    modifier = Modifier.fillMaxWidth(),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color.DarkGray
                                )
                            }
                        }
                        item {
                            AsyncImage(
                                model = state.pokemon?.image?.other?.frontImage?.urlImage,
                                contentDescription = "pokemon image",
                                placeholder = painterResource(drawable.imagem_padrao),
                                error = painterResource(drawable.imagem_padrao),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                            )
                        }
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                state.pokemon?.types?.let {
                                    it.forEach { type ->
                                        TypeItem(pokemonType = type)
                                    }
                                }
                            }
                            Spacer(Modifier.height(4.dp))
                        }
                        val height = state.pokemon?.height?.div(10f).toString()
                        val weight = state.pokemon?.weight?.div(10f).toString()
                        val dataMap = mapOf("Altura" to "$height m", "Peso" to "$weight kg")

                        val statsList = state.pokemon?.stats ?: emptyList()
                        val statMap = statsList.associate {
                            it.stat.name.replaceFirstChar { name ->
                                name.uppercase()
                            } to it.baseStats
                        }

                        val abilityList = state.pokemon?.abilities ?: emptyList()
                        val abilityMap = abilityList
                            .mapIndexed { index, ability ->
                                (index + 1).toString() to ability.ability.name.replaceFirstChar { it.uppercase() }
                            }.toMap()

                        item {
                            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                                PokemonInfoSection(sectionTitle = "Dados", infoMap = dataMap)
                                PokemonInfoSection(sectionTitle = "Stats", infoMap = statMap)
                                PokemonInfoSection(
                                    sectionTitle = "Habilidades",
                                    infoMap = abilityMap
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonDetailsScreenPreview() {
    PokemonDetailsScreen(
        state = PokemonDetailsUiState.Success(
            pokemon = pokemonSample
        )
    )
}