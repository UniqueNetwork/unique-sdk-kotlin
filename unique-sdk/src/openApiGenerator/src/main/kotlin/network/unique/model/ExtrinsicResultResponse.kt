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

import network.unique.model.BlockResult
import network.unique.model.ExtrinsicResultEvent
import network.unique.model.FeeResponse
import network.unique.model.MethodNameDto

import com.squareup.moshi.Json

/**
 * 
 *
 * @param isCompleted 
 * @param hash 
 * @param blockIndex 
 * @param error 
 * @param events 
 * @param callbackUrl 
 * @param createdAt 
 * @param block 
 * @param callMethod 
 * @param parsed 
 * @param fee 
 */


data class ExtrinsicResultResponse (

    @Json(name = "isCompleted")
    val isCompleted: kotlin.Boolean?,

    @Json(name = "hash")
    val hash: kotlin.String?,

    @Json(name = "blockIndex")
    val blockIndex: java.math.BigDecimal?,

    @Json(name = "error")
    val error: kotlin.Any?,

    @Json(name = "events")
    val events: kotlin.collections.List<ExtrinsicResultEvent>?,

    @Json(name = "callbackUrl")
    val callbackUrl: kotlin.String?,

    @Json(name = "createdAt")
    val createdAt: java.math.BigDecimal?,

    @Json(name = "block")
    val block: BlockResult?,

    @Json(name = "callMethod")
    val callMethod: MethodNameDto?,

    @Json(name = "parsed")
    val parsed: kotlin.Any? = null,

    @Json(name = "fee")
    val fee: FeeResponse? = null

)

