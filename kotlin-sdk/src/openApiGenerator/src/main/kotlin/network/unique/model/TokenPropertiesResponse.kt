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

import network.unique.model.TokenProperty

import com.squareup.moshi.Json

/**
 * 
 *
 * @param properties 
 */


data class TokenPropertiesResponse (

    @Json(name = "properties")
    val properties: kotlin.collections.List<TokenProperty>

)

