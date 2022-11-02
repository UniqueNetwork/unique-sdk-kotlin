package who.we.remote

import io.ktor.client.engine.cio.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import who.we.remote.model.BalanceTransferBody
import who.we.remote.model.SubmitTxBody
import who.we.remote.model.UnsignedTxPayloadBody
import java.math.BigDecimal

class ApiClientITTests {
    @Test
    fun transferFlowITTest() {
        runBlocking {
            val service: SdkService = SdkServiceImpl(CIO.create(), "rest.opal.uniquenetwork.dev", URLProtocol.HTTPS)
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

            Assertions.assertNotNull(extrinsic)
            Assertions.assertFalse(extrinsic.isCompleted)
        }
    }
}