package br.com.steventung.pokedex.network

import br.com.steventung.pokedex.common.utils.NetworkResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.prepareRequest
import io.ktor.client.request.setBody
import io.ktor.http.HttpMethod
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RequestHandler(val httpClient: HttpClient) {
    suspend inline fun <reified B, reified R> executeRequest(
        method: HttpMethod,
        urlPathSegment: List<Any>,
        body: B? = null,
        queryParams: Map<String, Any>? = null
    ): NetworkResult<R> {
        return withContext(Dispatchers.IO) {
            try {
                val response = httpClient.prepareRequest {
                    this.method = method
                    url {
                        val pathSegments = urlPathSegment.map { it.toString() }
                        appendPathSegments(pathSegments)
                    }
                    body?.let { setBody(it) }
                    queryParams?.let { params ->
                        params.forEach { (key, value) ->
                            parameter(key, value)
                        }
                    }
                }.execute().body<R>()
                NetworkResult.Success(response)
            } catch (e: Exception) {
                NetworkResult.Error(null, e)
            }
        }
    }

    suspend inline fun <reified R> get(
        urlPathSegments: List<Any>,
        queryParams: Map<String, Any>? = null
    ): NetworkResult<R> = executeRequest<Any, R>(
        method = HttpMethod.Get,
        urlPathSegment = urlPathSegments.toList(),
        queryParams = queryParams
    )

    suspend inline fun <reified B, reified R> post(
        urlPathSegments: List<Any>,
        body: B? = null
    ): NetworkResult<R> = executeRequest(
        method = HttpMethod.Post,
        urlPathSegment = urlPathSegments.toList(),
        body = body
    )
}