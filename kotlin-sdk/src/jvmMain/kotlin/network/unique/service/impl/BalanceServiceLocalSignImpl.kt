package network.unique.service.impl

import network.unique.model.UnsignedTxPayloadBody
import network.unique.model.balance.SignResponse
import network.unique.signer.CryptoScheme
import network.unique.signer.Pair

class BalanceServiceLocalSignImpl(clientWrapper: KtorClientWrapper) : BalanceServiceImpl(clientWrapper) {

    override suspend fun signTransaction(request: UnsignedTxPayloadBody, seed: String): SignResponse {
        val pair = Pair.fromSuri(CryptoScheme.Sr25519, "//Bob", null)

        val signature = pair.sign(toByteArray(request.signerPayloadRaw.data.substring(2)))
            .joinToString("") { eachByte -> "%02x".format(eachByte) }

        return SignResponse(request.signerPayloadJSON, "0x01$signature")
    }

    private fun toByteArray(data: String): ByteArray {
        return data.chunked(2)
            .map { it.toInt(16).toByte() }
            .toByteArray()
    }
}