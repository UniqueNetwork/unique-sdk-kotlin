package network.unique.model

import kotlinx.serialization.Serializable

@Serializable
data class SubmitResultResponse(
    val hash: String,
)