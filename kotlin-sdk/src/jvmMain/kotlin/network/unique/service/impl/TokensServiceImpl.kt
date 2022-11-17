package network.unique.service.impl

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import network.unique.model.tokens.TokenByIdResponse
import network.unique.service.TokensService

class TokensServiceImpl(clientWrapper: KtorClientWrapper) : BasicHttpService(clientWrapper), TokensService {

    override suspend fun getToken(at: String, collectionId: Long, tokenId: Long): TokenByIdResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = super.protocol
                host = super.host
                path("/v1/tokens")
                parameters.append("at", at)
                parameters.append("collectionId", collectionId.toString())
                parameters.append("tokenId", tokenId.toString())
            }

            accept(ContentType.Application.Json)
            method = HttpMethod.Get
        }
        validateResponse(response)
        return response.body()
    }

    override suspend fun createToken(at: String, collectionId: Long, tokenId: Long): TokenByIdResponse {
        TODO("Not yet implemented")
    }

    override suspend fun deleteToken(at: String, collectionId: Long, tokenId: Long): TokenByIdResponse {
        TODO("Not yet implemented")
    }

}