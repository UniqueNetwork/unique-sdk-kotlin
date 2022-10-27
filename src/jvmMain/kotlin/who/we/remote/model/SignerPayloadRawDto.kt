package who.we.remote.model

import kotlinx.serialization.Serializable

@Serializable
class SignerPayloadRawDto(
    val address: String,
    val data: String,
    val type: String,
)