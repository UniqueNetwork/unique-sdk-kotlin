package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
data class CollectionNestingPermissionsDto(
    val tokenOwner: Boolean,
    val collectionAdmin: Boolean,
    val restricted: Array<Long>
)
