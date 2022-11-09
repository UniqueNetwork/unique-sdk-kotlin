package network.unique.model

import kotlinx.serialization.Serializable

@Serializable
data class SubmitTxBody(
    val signerPayloadJSON: SignerPayloadJSONDto,
    val signature: String,
) : Feeable