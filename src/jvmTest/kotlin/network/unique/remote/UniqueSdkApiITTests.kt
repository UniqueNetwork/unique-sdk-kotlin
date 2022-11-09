package network.unique.remote

import io.ktor.client.engine.apache.*
import io.ktor.client.engine.cio.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import network.unique.client.KtorClientWrapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import network.unique.model.balance.BalanceTransferBody
import network.unique.model.SubmitTxBody
import network.unique.model.UnsignedTxPayloadBody
import network.unique.service.BalanceService
import network.unique.service.impl.BalanceServiceImpl
import network.unique.service.impl.BalanceServiceLocalSignImpl
import network.unique.service.impl.ExtrinsicServiceImpl
import java.math.BigDecimal

class ApiClientITTests {
    @Test
    fun transferFlowITTest() {
        runBlocking {
            val clientWrapper = KtorClientWrapper(CIO.create(), "rest.opal.uniquenetwork.dev", URLProtocol.HTTPS)
            val balanceService = BalanceServiceLocalSignImpl(clientWrapper)
            val extrinsicService = ExtrinsicServiceImpl(clientWrapper)
            val seed = "//Bob"
            val transferBody = BalanceTransferBody(
                "5FHneW46xGXgs5mUiveU4sbTyGBzmstUspZC92UhjJM694ty",
                "unjKJQJrRd238pkUZZvzDQrfKuM39zBSnQ5zjAGAGcdRhaJTx",
                BigDecimal("0.01")
            )

            val transferResponse = balanceService.buildTransaction(transferBody, seed)

            val signBody = UnsignedTxPayloadBody(transferResponse.signerPayloadJSON, transferResponse.signerPayloadRaw, transferResponse.signerPayloadHex)
            val signResponse = balanceService.signTransaction(signBody, seed)

            val submitBody = SubmitTxBody(signResponse.signerPayloadJSON, signResponse.signature)
            val submitResponse = balanceService.submitAndWatchTransaction(submitBody, seed)
            val extrinsic = extrinsicService.getExtrinsicStatus(submitResponse.hash)

            println(submitResponse.hash)
            println(extrinsic)

            Assertions.assertNotNull(extrinsic)
            Assertions.assertFalse(extrinsic.isCompleted)
        }
    }
}