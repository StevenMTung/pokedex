package br.com.steventung.pokedex.search.data

import br.com.steventung.pokedex.common.utils.NetworkResult
import br.com.steventung.pokedex.network.RequestHandler
import br.com.steventung.pokedex.network.model.PokemonResponse

class PokemonRepositoryImpl(
    private val requestHandler: RequestHandler
): PokemonRepository {
    override suspend fun getPokemonById(id: Int): NetworkResult<PokemonResponse> {
       return requestHandler.get(urlPathSegments = listOf("api", "v2", "pokemon", id))
    }
}