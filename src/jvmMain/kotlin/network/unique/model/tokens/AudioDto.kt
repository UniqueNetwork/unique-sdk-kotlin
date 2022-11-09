package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
data class AudioDto(
    val urlTemplate: String,
    val format: String,
    val isLossless: Boolean,
)
