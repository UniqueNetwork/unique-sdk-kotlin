package network.unique.service.impl

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import network.unique.model.SubmitResultResponse
import network.unique.model.SubmitTxBody
import network.unique.model.UnsignedTxPayloadBody
import network.unique.model.UnsignedTxPayloadResponse
import network.unique.model.balance.*
import network.unique.service.BalanceService

open class BalanceServiceImpl(clientWrapper: KtorClientWrapper) : BasicHttpService(clientWrapper), BalanceService {

    override suspend fun getBalance(address: String): AllBalanceResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = super.protocol
                host = super.host
                path("/v1/balance")
                parameters.append("address", address)
            }
            method = HttpMethod.Get
            accept(ContentType.Application.Json)
        }
        validateResponse(response)
        return response.body()
    }

    override suspend fun buildTransaction(request: BalanceTransferBody, seed: String): UnsignedTxPayloadResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = super.protocol
                host = super.host
                path("/v1/balance/transfer")
                parameters.append("use", "Build")
                parameters.append("withFee", "false")
                parameters.append("verify", "false")
            }
            headers {
                append(HttpHeaders.Authorization, "Seed $seed")
            }
            method = HttpMethod.Post
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        validateResponse(response)
        return response.body()
    }

    override suspend fun signTransaction(request: UnsignedTxPayloadBody, seed: String): SignResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = super.protocol
                host = super.host
                path("/v1/balance/transfer")
                parameters.append("use", "Sign")
                parameters.append("withFee", "false")
                parameters.append("verify", "false")
            }
            headers {
                append(HttpHeaders.Authorization, "Seed $seed")
            }
            method = HttpMethod.Post
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        validateResponse(response)
        return response.body()
    }

    override suspend fun submitAndWatchTransaction(request: SubmitTxBody, seed: String): SubmitResultResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = super.protocol
                host = super.host
                path("/v1/balance/transfer")
                parameters.append("use", "SubmitWatch")
                parameters.append("withFee", "false")
                parameters.append("verify", "false")
            }
            headers {
                append(HttpHeaders.Authorization, "Seed $seed")
            }
            method = HttpMethod.Post
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        validateResponse(response)
        return response.body()
    }

}