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
import network.unique.model.SetSponsorshipParsed

import com.squareup.moshi.Json

/**
 * 
 *
 * @param isError 
 * @param parsed 
 * @param fee 
 */


data class SetSponsorshipResponse (

    @Json(name = "isError")
    val isError: kotlin.Boolean,

    @Json(name = "parsed")
    val parsed: SetSponsorshipParsed,

    @Json(name = "fee")
    val fee: FeeResponse? = null

)

