package br.com.steventung.pokedex.di

import br.com.steventung.pokedex.network.HttpClientBuilder
import br.com.steventung.pokedex.network.RequestHandler
import br.com.steventung.pokedex.search.data.PokemonRepository
import br.com.steventung.pokedex.search.data.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.http.URLProtocol
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClientBuilder()
            .protocol(URLProtocol.HTTPS)
            .host("pokeapi.co")
            .build()
    }

    @Singleton
    @Provides
    fun provideRequestHandler(httpClient: HttpClient): RequestHandler {
        return RequestHandler(httpClient)
    }

    @Singleton
    @Provides
    fun providePokemonRepository(requestHandler: RequestHandler): PokemonRepository {
        return PokemonRepositoryImpl(requestHandler)
    }
}