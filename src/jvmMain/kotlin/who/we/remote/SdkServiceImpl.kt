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
import kotlinx.serialization.json.Json
import who.we.remote.exception.RequestException
import who.we.remote.model.*

class SdkServiceImpl(engine: HttpClientEngine, private val host: String, private val protocol: URLProtocol) : SdkService {

    private val client: HttpClient = HttpClient(engine) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 5)
            exponentialDelay()
        }
    }

    override suspend fun buildTransaction(request: BalanceTransferBody, seed: String): UnsignedTxPayloadResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = this@SdkServiceImpl.protocol
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
                protocol = this@SdkServiceImpl.protocol
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
                protocol = this@SdkServiceImpl.protocol
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
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        validateResponse(response)
        return response.body()
    }

    override suspend fun getExtrinsic(transactionHash: String, seed: String): ExtrinsicResultResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = this@SdkServiceImpl.protocol
                host = this@SdkServiceImpl.host
                path("/v1/extrinsic/status")
                parameters.append("hash", transactionHash)
            }
            headers {
                append(HttpHeaders.Authorization, "Seed $seed")
            }
            accept(ContentType.Application.Json)
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