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
 * @param foreign 
 * @param erc721metadata 
 */


data class CollectionFlags (

    @Json(name = "foreign")
    val foreign: kotlin.Boolean,

    @Json(name = "erc721metadata")
    val erc721metadata: kotlin.Boolean

)
