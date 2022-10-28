package who.we.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import who.we.remote.exception.RequestException
import who.we.remote.model.*

class SdkServiceImpl(private val host: String) : SdkService {

    private val client: HttpClient = HttpClient(CIO) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json()
        }
        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 5)
            exponentialDelay()
        }
    }

    override suspend fun buildTransaction(request: BalanceTransferBody, seed: String): UnsignedTxPayloadResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = URLProtocol.HTTPS
                host = this@SdkServiceImpl.host
                path("/v1/balance/transfer")
                parameters.append("use", "Build")
                parameters.append("withFee", "false")
                parameters.append("verify", "false")
            }
            headers {
                append(HttpHeaders.Authorization, "Seed $seed")
            }
            method = HttpMethod.Post
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        validateResponse(response)
        return response.body()
    }

    override suspend fun signTransaction(request: UnsignedTxPayloadBody, seed: String): SignResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = URLProtocol.HTTPS
                host = this@SdkServiceImpl.host
                path("/v1/balance/transfer")
                parameters.append("use", "Sign")
                parameters.append("withFee", "false")
                parameters.append("verify", "false")
            }
            headers {
                append(HttpHeaders.Authorization, "Seed $seed")
            }
            method = HttpMethod.Post
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        validateResponse(response)
        return response.body()
    }

    override suspend fun submitAndWatchTransaction(request: SubmitTxBody, seed: String): SubmitResultResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = URLProtocol.HTTPS
                host = this@SdkServiceImpl.host
                path("/v1/balance/transfer")
                parameters.append("use", "SubmitWatch")
                parameters.append("withFee", "false")
                parameters.append("verify", "false")
            }
            headers {
                append(HttpHeaders.Authorization, "Seed $seed")
            }
            method = HttpMethod.Post
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        validateResponse(response)
        return response.body()
    }

    override suspend fun getExtrinsic(transactionHash: String, seed: String): ExtrinsicResultResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = URLProtocol.HTTPS
                host = this@SdkServiceImpl.host
                path("/v1/extrinsic/status")
                parameters.append("hash", transactionHash)
            }
            headers {
                append(HttpHeaders.Authorization, "Seed $seed")
            }
            method = HttpMethod.Get
        }
        validateResponse(response)
        return response.body()
    }

    private fun validateResponse(response: HttpResponse) {
        val statusCodeType = response.status.value / 100

        if (statusCodeType != 2 && statusCodeType != 3) {
            throw RequestException(response, "Request error")
        }
    }

}