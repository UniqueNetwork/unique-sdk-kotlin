package who.we.remote.model

import kotlinx.serialization.Serializable

@Serializable
class SignResponse(
    val signerPayloadJSON: SignerPayloadJSONDto,
    val signature: String,
    val fee: FeeResponse? = null,
)