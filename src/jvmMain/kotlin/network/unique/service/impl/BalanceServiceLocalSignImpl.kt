package network.unique.service.impl

import network.unique.client.KtorClientWrapper
import network.unique.model.balance.SignResponse
import network.unique.model.UnsignedTxPayloadBody
import network.unique.signer.CryptoScheme
import network.unique.signer.Pair
import org.bouncycastle.util.encoders.Hex

class BalanceServiceLocalSignImpl(clientWrapper: KtorClientWrapper) : BalanceServiceImpl(clientWrapper) {

    override suspend fun signTransaction(request: UnsignedTxPayloadBody, seed: String): SignResponse {
        val pair = Pair.fromSuri(CryptoScheme.Sr25519, "//Bob", null)

        val signature = pair.sign(Hex.decode(request.signerPayloadRaw.data.substring(2)))
            .joinToString("") { eachByte -> "%02x".format(eachByte) }

        return SignResponse(request.signerPayloadJSON, "0x01$signature")
    }
}