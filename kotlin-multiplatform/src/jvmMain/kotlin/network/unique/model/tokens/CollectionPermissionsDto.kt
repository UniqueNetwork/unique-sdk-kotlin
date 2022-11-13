package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
data class CollectionPermissionsDto(
    val access: AccessPermission,
    val mintMode: Boolean,
    val nesting: CollectionNestingPermissionsDto,
)
