package who.we.remote.model

import kotlinx.serialization.Serializable

@Serializable
class SubmitTxBody(
    val signerPayloadJSON: SignerPayloadJSONDto,
    val signature: String,
)