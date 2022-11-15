package network.unique.model.balance

import kotlinx.serialization.Serializable
import network.unique.model.FeeResponse
import network.unique.model.SignerPayloadJSONDto

@Serializable
data class SignResponse(
    val signerPayloadJSON: SignerPayloadJSONDto,
    val signature: String,
    val fee: FeeResponse? = null,
)