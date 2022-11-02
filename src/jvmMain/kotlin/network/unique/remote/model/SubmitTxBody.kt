package network.unique.remote.model

import kotlinx.serialization.Serializable

@Serializable
class SubmitTxBody(
    val signerPayloadJSON: SignerPayloadJSONDto,
    val signature: String,
)