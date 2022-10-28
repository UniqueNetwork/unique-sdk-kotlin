package who.we.remote

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import who.we.remote.model.BalanceTransferBody
import who.we.remote.model.SubmitTxBody
import who.we.remote.model.UnsignedTxPayloadBody
import java.math.BigDecimal

class ApiClientTest {
    @Test
    fun sampleClientTest() {
        runBlocking {
//            val mockEngine = MockEngine {
//                respond(
//                    content = ByteReadChannel("""{"ip":"127.0.0.1"}"""),
//                    status = HttpStatusCode.OK,
//                    headers = headersOf(HttpHeaders.ContentType, "application/json")
//                )
//            }
//            val apiClient = SdkServiceImpl(mockEngine, "")
//
////            Assertions.assertEquals("127.0.0.1", apiClient.buildTransaction(BuildRequest()).toString())
            val service: SdkService = SdkServiceImpl("rest.opal.uniquenetwork.dev")
            val seed = "//Bob"
            val transferBody = BalanceTransferBody(
                "5FHneW46xGXgs5mUiveU4sbTyGBzmstUspZC92UhjJM694ty",
                "unjKJQJrRd238pkUZZvzDQrfKuM39zBSnQ5zjAGAGcdRhaJTx",
                BigDecimal("0.01")
            )

            val transferResponse = service.buildTransaction(transferBody, seed)

            val signBody = UnsignedTxPayloadBody(transferResponse.signerPayloadJSON, transferResponse.signerPayloadRaw, transferResponse.signerPayloadHex)
            val signResponse = service.signTransaction(signBody, seed)

            val submitBody = SubmitTxBody(signResponse.signerPayloadJSON, signResponse.signature)
            val submitResponse = service.submitAndWatchTransaction(submitBody, seed)
            val extrinsic = service.getExtrinsic(submitResponse.hash, seed)

            println(submitResponse.hash)
            println(extrinsic)
        }
    }
}