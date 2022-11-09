package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
enum class MetaUpdatePermission {
    ItemOwner,
    Admin,
    None
}