package network.unique.model.extrinsic

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray

@Serializable
data class ExtrinsicResultEvent(
    val section: String,
    val method: String,
    val data: JsonArray? = null,
)