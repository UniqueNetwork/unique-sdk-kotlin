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


import com.squareup.moshi.Json

/**
 * 
 *
 * @param mutable 
 * @param collectionAdmin 
 * @param tokenOwner 
 */


data class PropertyPermission (

    @Json(name = "mutable")
    val mutable: kotlin.Boolean = true,

    @Json(name = "collectionAdmin")
    val collectionAdmin: kotlin.Boolean = true,

    @Json(name = "tokenOwner")
    val tokenOwner: kotlin.Boolean = true

)

