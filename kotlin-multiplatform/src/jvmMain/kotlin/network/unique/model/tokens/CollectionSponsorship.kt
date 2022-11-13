package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
data class CollectionSponsorship(
    val address: String,
    val isConfirmed: Boolean,
)
