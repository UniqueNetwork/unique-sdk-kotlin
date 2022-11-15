package network.unique.model

import kotlinx.serialization.Serializable

@Serializable
data class SignerPayloadRawDto(
    val address: String,
    val data: String,
    val type: String,
)