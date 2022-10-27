package who.we.remote.model

import kotlinx.serialization.Serializable

@Serializable
class UnsignedTxPayloadBody(
    val signerPayloadJSON: SignerPayloadJSONDto,
    val signerPayloadRaw: SignerPayloadRawDto,
    val signerPayloadHexFee: FeeResponse,
)