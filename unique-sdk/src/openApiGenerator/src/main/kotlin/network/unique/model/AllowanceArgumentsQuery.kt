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

import network.unique.model.TokenIdQueryCollectionId

import com.squareup.moshi.Json

/**
 * 
 *
 * @param collectionId 
 * @param tokenId 
 * @param from The ss-58 encoded address
 * @param to The ss-58 encoded address
 * @param at Hash of execution block
 */


data class AllowanceArgumentsQuery (

    @Json(name = "collectionId")
    val collectionId: TokenIdQueryCollectionId,

    @Json(name = "tokenId")
    val tokenId: java.math.BigDecimal,

    /* The ss-58 encoded address */
    @Json(name = "from")
    val from: kotlin.String,

    /* The ss-58 encoded address */
    @Json(name = "to")
    val to: kotlin.String,

    /* Hash of execution block */
    @Json(name = "at")
    val at: kotlin.String? = null

)
