package network.unique.remote

import io.ktor.client.engine.*
import io.ktor.http.*
import network.unique.remote.model.SignResponse
import network.unique.remote.model.UnsignedTxPayloadBody
import network.unique.signer.CryptoScheme
import network.unique.signer.Pair
import org.bouncycastle.util.encoders.Hex


class SdkServiceLocalSignImpl(engine: HttpClientEngine, host: String, protocol: URLProtocol) :
    SdkServiceImpl(engine, host, protocol) {

    override suspend fun signTransaction(request: UnsignedTxPayloadBody, seed: String): SignResponse {
        val pair = Pair.fromSuri(CryptoScheme.Sr25519, "//Bob", null)

        val signature = pair.sign(Hex.decode(request.signerPayloadRaw.data.substring(2)))
            .joinToString("") { eachByte -> "%02x".format(eachByte) }

        return SignResponse(request.signerPayloadJSON, "0x01$signature")
    }
}