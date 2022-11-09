package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
data class TokenProperty(
    val key: String,
    val value: String,
)
