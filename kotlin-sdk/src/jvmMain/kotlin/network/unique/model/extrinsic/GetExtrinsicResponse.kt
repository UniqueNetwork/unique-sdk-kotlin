package network.unique.model.extrinsic

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray

@Serializable
data class GetExtrinsicResponse(
    val section: String,
    val method: String,
    val nonce: Long,
    val signer: String,
)