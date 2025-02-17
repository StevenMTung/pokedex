package br.com.steventung.pokedex.search.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.steventung.pokedex.common.navigation.pokemonIdArgument
import br.com.steventung.pokedex.common.utils.NetworkResult
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
class PokemonDetailsViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<PokemonDetailsUiState>(PokemonDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val pokemonId = savedStateHandle.get<Int>(pokemonIdArgument)

    init {
        loadPokemonInfo()
    }

    fun loadPokemonInfo() {
        _uiState.update { PokemonDetailsUiState.Loading }
        pokemonId?.let { id ->
            viewModelScope.launch {
                getPokemonUseCase.invoke(id).collect { result ->
                    when (result) {
                        is NetworkResult.Error ->
                            when (result.exception) {
                                is UnresolvedAddressException -> {
                                    delay(500)
                                    _uiState.update {
                                        PokemonDetailsUiState.Error(
                                            errorMessage = "Falha de conexão, verifique sua rede"
                                        )
                                    }
                                }

                                else -> {
                                    delay(500)
                                    _uiState.update {
                                        PokemonDetailsUiState.Error(
                                            errorMessage = "Erro ao carregar informações"
                                        )
                                    }
                                }
                            }

                        is NetworkResult.Success -> _uiState.update {
                            PokemonDetailsUiState.Success(pokemon = result.result)
                        }
                    }
                }
            }
        }
    }


}