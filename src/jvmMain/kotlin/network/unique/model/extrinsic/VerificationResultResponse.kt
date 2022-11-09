package network.unique.model.extrinsic

import kotlinx.serialization.Serializable

@Serializable
data class VerificationResultResponse(
    val isValid: Boolean,
    val errorMessage: String
)