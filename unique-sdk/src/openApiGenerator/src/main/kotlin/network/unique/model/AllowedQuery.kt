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
 * @param collectionId 
 * @param account The ss-58 encoded address
 * @param at Hash of execution block
 */


data class AllowedQuery (

    @Json(name = "collectionId")
    val collectionId: java.math.BigDecimal? = null,

    /* The ss-58 encoded address */
    @Json(name = "account")
    val account: kotlin.String? = null,

    /* Hash of execution block */
    @Json(name = "at")
    val at: kotlin.String? = null

)

