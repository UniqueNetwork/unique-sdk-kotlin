package who.we.remote.model

import kotlinx.serialization.Serializable

@Serializable
class UnsignedTxPayloadResponse(
    val signerPayloadJSON: SignerPayloadJSONDto,
    val signerPayloadRaw: SignerPayloadRawDto,
    val signerPayloadHex: String,
    val fee: FeeResponse? = null,
)