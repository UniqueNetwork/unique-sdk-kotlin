package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
data class CollectionProperty(
    val key: String,
    val value: String,
)
