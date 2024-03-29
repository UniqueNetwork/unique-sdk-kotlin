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
 * A JSON object containing the metadata associated with an account
 *
 * @param encoded 
 * @param encoding 
 * @param address 
 * @param meta 
 */


data class AccountDataResponseKeyfile (

    @Json(name = "encoded")
    val encoded: kotlin.String? = null,

    @Json(name = "encoding")
    val encoding: kotlin.Any? = null,

    @Json(name = "address")
    val address: kotlin.String? = null,

    @Json(name = "meta")
    val meta: kotlin.Any? = null

)

