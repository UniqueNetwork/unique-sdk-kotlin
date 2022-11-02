package network.unique.remote.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray

@Serializable
class ExtrinsicResultEvent(
    val section: String,
    val method: String,
    val data: JsonArray? = null,
)