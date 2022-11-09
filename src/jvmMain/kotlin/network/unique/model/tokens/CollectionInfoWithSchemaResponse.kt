package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
data class CollectionInfoWithSchemaResponse(
    val mode: TokenMode,
    val name: String,
    val description: String,
    val tokenPrefix: String,
    val sponsorship: CollectionSponsorship,
    val limits: CollectionLimitsDto,
    val metaUpdatePermission: MetaUpdatePermission,
    val permission: CollectionPermissionsDto,
    val readOnly: Boolean,
    val id: Long,
    val owner: String,
    val schema: UniqueCollectionSchemaDecodedDto,
    val properties: Array<CollectionProperty>,
    val flags: CollectionFlags,
    val tokenPropertyPermissions: Array<CollectionPropertyKeyPermission>,
)