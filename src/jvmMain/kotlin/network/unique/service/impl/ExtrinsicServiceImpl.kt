package network.unique.service.impl

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import network.unique.client.KtorClientWrapper
import network.unique.model.*
import network.unique.model.extrinsic.*
import network.unique.service.ExtrinsicService

class ExtrinsicServiceImpl(clientWrapper: KtorClientWrapper) : BasicHttpService(clientWrapper), ExtrinsicService {
    override suspend fun buildExtrinsic(request: TxBuildBody): UnsignedTxPayloadResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = super.protocol
                host = super.host
                path("/v1/extrinsic/build")
            }
            method = HttpMethod.Post
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        validateResponse(response)
        return response.body()
    }

    override suspend fun signExtrinsic(request: UnsignedTxPayloadBody, seed: String): SignTxResultResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = super.protocol
                host = super.host
                path("/v1/extrinsic/sign")
            }
            method = HttpMethod.Post
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        validateResponse(response)
        return response.body()
    }

    override suspend fun verifyExtrinsicSign(request: SubmitTxBody): VerificationResultResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = super.protocol
                host = super.host
                path("/v1/extrinsic/verify-sign")
            }
            method = HttpMethod.Post
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        validateResponse(response)
        return response.body()
    }

    override suspend fun submitExtrinsic(request: SubmitTxBody): SubmitResultResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = super.protocol
                host = super.host
                path("/v1/extrinsic/submit")
            }
            method = HttpMethod.Post
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        validateResponse(response)
        return response.body()
    }

    override suspend fun calculateExtrinsicFee(request: Feeable): FeeResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = super.protocol
                host = super.host
                path("/v1/extrinsic/calculate-fee")
            }
            method = HttpMethod.Post
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        validateResponse(response)
        return response.body()
    }

    override suspend fun getExtrinsicStatus(transactionHash: String): ExtrinsicResultResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = super.protocol
                host = super.host
                path("/v1/extrinsic/status")
                parameters.append("hash", transactionHash)
            }
            accept(ContentType.Application.Json)
            method = HttpMethod.Get
        }
        validateResponse(response)
        return response.body()
    }

    override suspend fun getExtrinsic(blockHashOrNumber: String, extrinsicHash: String): GetExtrinsicResponse {
        val response: HttpResponse = client.request {
            url {
                protocol = super.protocol
                host = super.host
                path("/v1/extrinsic")
                parameters.append("blockHashOrNumber", blockHashOrNumber)
                parameters.append("extrinsicHash", extrinsicHash)
            }
            accept(ContentType.Application.Json)
            method = HttpMethod.Get
        }
        validateResponse(response)
        return response.body()
    }

}