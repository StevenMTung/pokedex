package br.com.steventung.pokedex.search.ui.screens.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.steventung.pokedex.common.utils.NetworkResult
import br.com.steventung.pokedex.search.domain.model.PokemonDomain
import br.com.steventung.pokedex.search.domain.usecase.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<PokemonListUiState>(PokemonListUiState.Loading())
    val uiState = _uiState.asStateFlow()

    private val pokemonList = mutableListOf<PokemonDomain>()
    private val initialIndex = 1
    private val finalIndex = 151

    init {
        loadPokemonList()
    }

    fun loadPokemonList() {
        var error = false
        viewModelScope.launch {
            var pokemonProgressLoading = 0f
            pokemonList.clear()
            _uiState.update { PokemonListUiState.Loading() }
            delay(1000)
            for (i in initialIndex..finalIndex) {
                getPokemonUseCase.invoke(i).collect { response ->
                    when (response) {
                        is NetworkResult.Error -> {
                            onEvent(UiEvent.OnShowErrorMessage(response.exception))
                            error = true
                        }

                        is NetworkResult.Success -> {
                            pokemonList.add(response.result)
                            pokemonProgressLoading++
                            onEvent(
                                UiEvent.OnUpdateLoadingProgress(
                                    progressValue = pokemonProgressLoading.div(
                                        finalIndex - initialIndex + 1
                                    )
                                )
                            )
                        }
                    }
                }
                if (error) return@launch
            }
            onEvent(event = UiEvent.OnShowPokemonList(pokemonList = pokemonList))
        }
    }

    fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.OnShowPokemonList -> {
                _uiState.update { PokemonListUiState.Success(pokemons = event.pokemonList) }
            }

            is UiEvent.OnShowErrorMessage -> {
                when (event.exception) {
                    is UnresolvedAddressException -> {
                        _uiState.update { PokemonListUiState.Error(errorMessage = "Falha de conexão, verifique sua rede") }
                    }

                    else -> _uiState.update { PokemonListUiState.Error(errorMessage = "Erro ao carregar informações") }
                }
            }

            is UiEvent.OnUpdateLoadingProgress -> _uiState.update {
                PokemonListUiState.Loading(
                    loadingProgress = event.progressValue
                )
            }

            is UiEvent.OnSearchTextChanged -> {
                if (event.textValue.isNotBlank()) {
                    val searchedList = pokemonList.filter {
                        it.name.lowercase().contains(event.textValue.lowercase())
                    }
                    _uiState.update {
                        PokemonListUiState.Success(
                            searchText = event.textValue,
                            pokemons = searchedList
                        )
                    }
                } else {
                    _uiState.update { PokemonListUiState.Success(pokemons = pokemonList) }
                }
            }
        }
    }

    sealed class UiEvent {
        data class OnShowPokemonList(val pokemonList: List<PokemonDomain>) : UiEvent()
        data class OnShowErrorMessage(val exception: Exception) : UiEvent()
        data class OnUpdateLoadingProgress(val progressValue: Float) : UiEvent()
        data class OnSearchTextChanged(val textValue: String) : UiEvent()
    }
}