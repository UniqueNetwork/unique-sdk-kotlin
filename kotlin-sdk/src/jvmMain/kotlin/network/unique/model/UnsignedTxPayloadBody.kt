package network.unique.model

import kotlinx.serialization.Serializable
import network.unique.model.SignerPayloadJSONDto
import network.unique.model.SignerPayloadRawDto

@Serializable
data class UnsignedTxPayloadBody(
    val signerPayloadJSON: SignerPayloadJSONDto,
    val signerPayloadRaw: SignerPayloadRawDto,
    val signerPayloadHex: String,
) : Feeable