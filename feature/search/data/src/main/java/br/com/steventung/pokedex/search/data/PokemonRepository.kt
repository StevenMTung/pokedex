package br.com.steventung.pokedex.search.data

import br.com.steventung.pokedex.common.utils.NetworkResult
import br.com.steventung.pokedex.network.model.PokemonResponse

interface PokemonRepository {
    suspend fun getPokemonById(id: Int): NetworkResult<PokemonResponse>
}