package network.unique.model.extrinsic

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import network.unique.model.Feeable

@Serializable
data class TxBuildBody(
    val address: String,
    val section: String,
    val method: String,
    val args: JsonElement? = null,
) : Feeable