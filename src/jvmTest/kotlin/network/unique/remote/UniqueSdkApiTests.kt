package network.unique.remote

import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import io.ktor.client.engine.apache.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import network.unique.client.KtorClientWrapper
import org.junit.jupiter.api.Test
import network.unique.model.balance.BalanceTransferBody
import network.unique.model.SubmitTxBody
import network.unique.model.UnsignedTxPayloadBody
import network.unique.service.impl.BalanceServiceImpl
import network.unique.service.impl.ExtrinsicServiceImpl

@WireMockTest
class ApiClientTests {

    @Test
    fun transferFlowTest(runtime :WireMockRuntimeInfo) {
        stubFor(post("/v1/balance/transfer?use=Build&withFee=false&verify=false")
            .willReturn(
                aResponse()
                    .withStatus(201)
                    .withHeader("Content-Type", "application/json")
                    .withBody(readText("/response/buildTransactionResponse.json"))
            )
        )
        stubFor(post("/v1/balance/transfer?use=Sign&withFee=false&verify=false")
            .willReturn(
                aResponse()
                    .withStatus(201)
                    .withHeader("Content-Type", "application/json")
                    .withBody(readText("/response/signTransactionResponse.json"))
            )
        )
        stubFor(post("/v1/balance/transfer?use=SubmitWatch&withFee=false&verify=false")
            .willReturn(
                aResponse()
                    .withStatus(201)
                    .withHeader("Content-Type", "application/json")
                    .withBody(readText("/response/submitWatchResponse.json"))
            )
        )
        stubFor(get("/v1/extrinsic/status?hash=0xa608132e03674235546438e2d7cd15086abb01233a7c476b62ecfeea02ec7d73")
            .willReturn(
                aResponse()
                    .withStatus(201)
                    .withHeader("Content-Type", "application/json")
                    .withBody(readText("/response/ExtrinsicResultResponse.json"))
            )
        )

        runBlocking {
            val clientWrapper = KtorClientWrapper(Apache.create(), "localhost:" + runtime.httpPort, URLProtocol.HTTP)
            val balanceService = BalanceServiceImpl(clientWrapper)
            val extrinsicService = ExtrinsicServiceImpl(clientWrapper)

            val balanceTransferBodyRequest = readObject("/request/BalanceTransferBody.json") {
                Json.decodeFromString<BalanceTransferBody>(it)
            }
            balanceService.buildTransaction(balanceTransferBodyRequest, "")

            val unsignedTxPayloadBody = readObject("/request/UnsignedTxPayloadBody.json") {
                Json.decodeFromString<UnsignedTxPayloadBody>(it)
            }
            balanceService.signTransaction(unsignedTxPayloadBody, "")

            val submitTxBody = readObject("/request/SubmitTxBody.json") {
                Json.decodeFromString<SubmitTxBody>(it)
            }
            val submitResponse = balanceService.submitAndWatchTransaction(submitTxBody, "")

            extrinsicService.getExtrinsicStatus(submitResponse.hash)
        }
    }

    private fun <T> readObject(fileName: String, decoder: (String) -> T) : T {
        val json = readText(fileName)
        return decoder.invoke(json)
    }

    private fun readText(fileName: String): String {
        return ApiClientTests::class.java.getResource(fileName)?.readText() ?: throw RuntimeException("No file present")
    }
}