package network.unique.remote

import kotlinx.coroutines.runBlocking
import network.unique.model.*
import network.unique.service.impl.ExtrinsicServiceImpl
import network.unique.service.impl.balance.TransferMutationServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ApiClientITTests {
    @Test
    fun transferFlowITTest() {
        runBlocking {
            val signerWrapper = Sr25519SignerWrapper("//Bob", null);
            val transferService = TransferMutationServiceImpl(signerWrapper, "https://rest.opal.uniquenetwork.dev")
            val extrinsicService = ExtrinsicServiceImpl("https://rest.opal.uniquenetwork.dev")
            val transferBody = TransferBody(
                "5FHneW46xGXgs5mUiveU4sbTyGBzmstUspZC92UhjJM694ty",
                "unjKJQJrRd238pkUZZvzDQrfKuM39zBSnQ5zjAGAGcdRhaJTx",
                BigDecimal("0.01")
            )

            val transferResponse = transferService.build(transferBody)

            val signBody = UnsignedTxPayloadResponse(transferResponse.signerPayloadJSON, transferResponse.signerPayloadRaw, transferResponse.signerPayloadHex)
            val signResponse = transferService.sign(signBody, "")

            val submitBody = SubmitTxBody(signResponse.signerPayloadJSON, signResponse.signature)
            val submitResponse = transferService.submitWatch(submitBody)
            val extrinsic = extrinsicService.getExtrinsicStatus(submitResponse.hash)

            println(submitResponse.hash)
            println(extrinsic)

            Assertions.assertNotNull(extrinsic)
            Assertions.assertFalse(extrinsic.isCompleted!!)
        }
    }
}