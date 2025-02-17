package br.com.steventung.pokedex.search.domain.usecase

import br.com.steventung.pokedex.common.utils.NetworkResult
import br.com.steventung.pokedex.search.data.PokemonRepository
import br.com.steventung.pokedex.search.domain.model.PokemonDomain
import br.com.steventung.pokedex.search.domain.model.toPokemonDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
     operator fun invoke(id: Int): Flow<NetworkResult<PokemonDomain>> = flow {
         when (val result = pokemonRepository.getPokemonById(id)) {
            is NetworkResult.Error -> emit(NetworkResult.Error(body = null, exception = result.exception))
            is NetworkResult.Success -> emit(NetworkResult.Success(result.result.toPokemonDomain()))
        }
    }
}