package who.we.remote.model

import kotlinx.serialization.Serializable

@Serializable
class ExtrinsicResultEvent(
    val section: String,
    val method: String,
    val data: Array<String>? = null,
)