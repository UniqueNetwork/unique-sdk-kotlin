package network.unique.model.extrinsic

import kotlinx.serialization.Serializable

@Serializable
data class SignTxResultResponse(
    val signature: String,
    val signatureType: SignatureType
)