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
 * @param address The ss-58 encoded address
 * @param destination The ss-58 encoded address
 * @param amount 
 */


data class BalanceTransferBody (

    /* The ss-58 encoded address */
    @Json(name = "address")
    val address: kotlin.String? = null,

    /* The ss-58 encoded address */
    @Json(name = "destination")
    val destination: kotlin.String? = null,

    @Json(name = "amount")
    val amount: java.math.BigDecimal? = null,

)

