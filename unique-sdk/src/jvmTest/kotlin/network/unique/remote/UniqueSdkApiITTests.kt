package network.unique.remote

import kotlinx.coroutines.runBlocking
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.impl.ExtrinsicServiceImpl
import network.unique.service.impl.balance.TransferMutationServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ApiClientITTests {

    @Test
    fun transferFlowITTest() {
        runBlocking {
            val signerWrapper = Sr25519SignerWrapper("charge fame control elephant taxi among brain latin meadow oven crash another", null, false)
            UniqueSdk.signerWrapper = signerWrapper
            val sdk = UniqueSdk("https://rest.opal.uniquenetwork.dev");
            val balanceService = sdk.balanceService;
            val transferService = balanceService.getTransfer()
            val extrinsicService = ExtrinsicServiceImpl("https://rest.opal.uniquenetwork.dev")
            val transferBody = TransferMutationRequest(
                "5DnUE1uV7iW25bUriWVPHY67KMm2t6g5v23GzVbZCUc8fyBD",
                "unjKJQJrRd238pkUZZvzDQrfKuM39zBSnQ5zjAGAGcdRhaJTx",
                BigDecimal("0.01")
            )

            val transferResponse = transferService.build(transferBody)

            val signBody = UnsignedTxPayloadResponse(transferResponse.signerPayloadJSON, transferResponse.signerPayloadRaw, transferResponse.signerPayloadHex)
            val signResponse = transferService.sign(signBody)

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