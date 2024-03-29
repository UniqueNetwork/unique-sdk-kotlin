/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package network.unique.model

import network.unique.model.CollectionFlags
import network.unique.model.CollectionLimitsDto
import network.unique.model.CollectionPermissionsDto
import network.unique.model.CollectionProperty
import network.unique.model.CollectionPropertyKeyPermission
import network.unique.model.CollectionSponsorship
import network.unique.model.UniqueCollectionSchemaDecodedDto

import com.squareup.moshi.Json

/**
 * 
 *
 * @param name 
 * @param description 
 * @param tokenPrefix 
 * @param id 
 * @param owner The ss-58 encoded address
 * @param properties 
 * @param flags 
 * @param tokenPropertyPermissions 
 * @param mode 
 * @param sponsorship 
 * @param limits 
 * @param metaUpdatePermission 
 * @param permissions 
 * @param readOnly 
 * @param schema 
 * @param knownSchema 
 */


data class CollectionInfoWithSchemaResponse (

    @Json(name = "name")
    val name: kotlin.String? = null,

    @Json(name = "description")
    val description: kotlin.String? = null,

    @Json(name = "tokenPrefix")
    val tokenPrefix: kotlin.String? = null,

    @Json(name = "id")
    val id: java.math.BigDecimal? = null,

    /* The ss-58 encoded address */
    @Json(name = "owner")
    val owner: kotlin.String? = null,

    @Json(name = "properties")
    val properties: kotlin.collections.List<CollectionProperty>? = null,

    @Json(name = "flags")
    val flags: CollectionFlags? = null,

    @Json(name = "tokenPropertyPermissions")
    val tokenPropertyPermissions: kotlin.collections.List<CollectionPropertyKeyPermission>? = null,

    @Json(name = "mode")
    val mode: CollectionInfoWithSchemaResponse.Mode? = null,

    @Json(name = "sponsorship")
    val sponsorship: CollectionSponsorship? = null,

    @Json(name = "limits")
    val limits: CollectionLimitsDto? = null,

    @Json(name = "metaUpdatePermission")
    val metaUpdatePermission: CollectionInfoWithSchemaResponse.MetaUpdatePermission? = null,

    @Json(name = "permissions")
    val permissions: CollectionPermissionsDto? = null,

    @Json(name = "readOnly")
    val readOnly: kotlin.Boolean? = null,

    @Json(name = "schema")
    val schema: UniqueCollectionSchemaDecodedDto? = null,

    @Json(name = "knownSchema")
    val knownSchema: CollectionInfoWithSchemaResponse.KnownSchema? = null

) {

    /**
     * 
     *
     * Values: nFT,fungible,reFungible
     */
    enum class Mode(val value: kotlin.String) {
        @Json(name = "NFT") nFT("NFT"),
        @Json(name = "Fungible") fungible("Fungible"),
        @Json(name = "ReFungible") reFungible("ReFungible");
    }
    /**
     * 
     *
     * Values: itemOwner,admin,none
     */
    enum class MetaUpdatePermission(val value: kotlin.String) {
        @Json(name = "ItemOwner") itemOwner("ItemOwner"),
        @Json(name = "Admin") admin("Admin"),
        @Json(name = "None") none("None");
    }
    /**
     * 
     *
     * Values: unique,old,eRC721Metadata
     */
    enum class KnownSchema(val value: kotlin.String) {
        @Json(name = "unique") unique("unique"),
        @Json(name = "_old_") old("_old_"),
        @Json(name = "ERC721Metadata") eRC721Metadata("ERC721Metadata");
    }
}

