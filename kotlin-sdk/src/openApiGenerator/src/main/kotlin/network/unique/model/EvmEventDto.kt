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
 * @param name 
 * @param propertyValues 
 */


data class EvmEventDto (

    @Json(name = "name")
    val name: kotlin.String,

    @Json(name = "values")
    val propertyValues: kotlin.Any

)

