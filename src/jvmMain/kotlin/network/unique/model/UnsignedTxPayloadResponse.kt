package network.unique.model

import kotlinx.serialization.Serializable

@Serializable
data class UnsignedTxPayloadResponse(
    val signerPayloadJSON: SignerPayloadJSONDto,
    val signerPayloadRaw: SignerPayloadRawDto,
    val signerPayloadHex: String,
    val fee: FeeResponse? = null,
)