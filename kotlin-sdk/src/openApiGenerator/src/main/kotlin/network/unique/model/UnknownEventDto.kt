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
 * @param topics 
 * @param `data` 
 */


data class UnknownEventDto (

    @Json(name = "topics")
    val topics: kotlin.collections.List<kotlin.String>,

    @Json(name = "data")
    val `data`: kotlin.String

)
