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
 * @param raw 
 * @param amount 
 * @param formatted 
 * @param unit 
 * @param decimals 
 */


data class FeeResponse (

    @Json(name = "raw")
    val raw: kotlin.String,

    @Json(name = "amount")
    val amount: kotlin.String,

    @Json(name = "formatted")
    val formatted: kotlin.String,

    @Json(name = "unit")
    val unit: kotlin.String,

    @Json(name = "decimals")
    val decimals: java.math.BigDecimal

)

