package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
data class CollectionPropertyKeyPermission(
    val key: String,
    val permission: CollectionPropertyKeyPermissionDto,
)
