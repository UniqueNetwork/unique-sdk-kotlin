package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
data class CollectionPropertyKeyPermissionDto(
    val mutable: Boolean,
    val collectionAdmin: Boolean,
    val tokenOwner: Boolean,
)
