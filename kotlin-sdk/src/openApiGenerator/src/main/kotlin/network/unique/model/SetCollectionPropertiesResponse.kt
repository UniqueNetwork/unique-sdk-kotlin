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

import network.unique.model.FeeResponse
import network.unique.model.SetCollectionPropertiesParsed

import com.squareup.moshi.Json

/**
 * 
 *
 * @param isError 
 * @param parsed 
 * @param fee 
 */


data class SetCollectionPropertiesResponse (

    @Json(name = "isError")
    val isError: kotlin.Boolean,

    @Json(name = "parsed")
    val parsed: SetCollectionPropertiesParsed,

    @Json(name = "fee")
    val fee: FeeResponse? = null

)
