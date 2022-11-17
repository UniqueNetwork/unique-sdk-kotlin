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

import network.unique.model.BuildBatchPayloadsResponse
import network.unique.model.EvmSendResponse
import network.unique.model.EvmSendResultParsed
import network.unique.model.FeeResponse
import network.unique.model.SignResponse
import network.unique.model.SignerPayloadJSONDto
import network.unique.model.SignerPayloadRawDto
import network.unique.model.SubmitResponse
import network.unique.model.UnsignedTxPayloadResponse

import com.squareup.moshi.Json

/**
 * 
 *
 * @param signerPayloadJSON 
 * @param signerPayloadRaw 
 * @param signerPayloadHex 
 * @param signature Warning: Signature must be with SignatureType!
 * @param hash 
 * @param isError 
 * @param parsed 
 * @param payloads 
 * @param nextNonce 
 * @param fee 
 */


data class EvmSendMutationDefaultResponse (

    @Json(name = "signerPayloadJSON")
    val signerPayloadJSON: SignerPayloadJSONDto,

    @Json(name = "signerPayloadRaw")
    val signerPayloadRaw: SignerPayloadRawDto,

    @Json(name = "signerPayloadHex")
    val signerPayloadHex: kotlin.String,

    /* Warning: Signature must be with SignatureType! */
    @Json(name = "signature")
    val signature: kotlin.String,

    @Json(name = "hash")
    val hash: kotlin.String,

    @Json(name = "isError")
    val isError: kotlin.Boolean,

    @Json(name = "parsed")
    val parsed: EvmSendResultParsed,

    @Json(name = "payloads")
    val payloads: kotlin.collections.List<UnsignedTxPayloadResponse>,

    @Json(name = "nextNonce")
    val nextNonce: java.math.BigDecimal,

    @Json(name = "fee")
    val fee: FeeResponse? = null

)

