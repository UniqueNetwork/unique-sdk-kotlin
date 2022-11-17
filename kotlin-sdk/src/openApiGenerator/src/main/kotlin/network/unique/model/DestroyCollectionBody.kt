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
 * @param collectionId 
 */


data class DestroyCollectionBody (

    /* The ss-58 encoded address */
    @Json(name = "address")
    val address: kotlin.String,

    @Json(name = "collectionId")
    val collectionId: java.math.BigDecimal

)

