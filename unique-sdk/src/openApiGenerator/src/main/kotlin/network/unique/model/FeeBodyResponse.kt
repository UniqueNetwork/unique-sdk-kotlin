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

import com.squareup.moshi.Json

/**
 * 
 *
 * @param fee 
 */


data class FeeBodyResponse (

    @Json(name = "fee")
    val fee: FeeResponse? = null,

)

