package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
data class SpatialObjectDto(
    val urlTemplate: String,
    val format: String,
)
