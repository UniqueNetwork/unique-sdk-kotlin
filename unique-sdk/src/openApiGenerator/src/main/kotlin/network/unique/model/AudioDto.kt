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
 * @param format 
 * @param urlTemplate 
 * @param isLossless 
 */


data class AudioDto (

    @Json(name = "format")
    val format: kotlin.String? = null,

    @Json(name = "urlTemplate")
    val urlTemplate: kotlin.String? = null,

    @Json(name = "isLossless")
    val isLossless: kotlin.Boolean? = null

)

