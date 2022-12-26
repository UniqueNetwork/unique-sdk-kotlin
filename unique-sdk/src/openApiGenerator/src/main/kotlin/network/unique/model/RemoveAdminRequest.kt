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

import network.unique.model.RemoveCollectionAdminBody
import network.unique.model.SignerPayloadJSONDto
import network.unique.model.SignerPayloadRawDto
import network.unique.model.SubmitTxBody
import network.unique.model.UnsignedTxPayloadBody

import com.squareup.moshi.Json

/**
 * 
 *
 * @param address The ss-58 encoded address
 * @param collectionId 
 * @param admin The ss-58 encoded address
 * @param signerPayloadJSON 
 * @param signerPayloadRaw 
 * @param signerPayloadHex 
 * @param signature Warning: Signature must be with SignatureType!
 */


data class RemoveAdminRequest (

    /* The ss-58 encoded address */
    @Json(name = "address")
    val address: kotlin.String? = null,

    @Json(name = "collectionId")
    val collectionId: java.math.BigDecimal? = null,

    /* The ss-58 encoded address */
    @Json(name = "admin")
    val admin: kotlin.String? = null,

    @Json(name = "signerPayloadJSON")
    val signerPayloadJSON: SignerPayloadJSONDto? = null,

    @Json(name = "signerPayloadRaw")
    val signerPayloadRaw: SignerPayloadRawDto? = null,

    @Json(name = "signerPayloadHex")
    val signerPayloadHex: kotlin.String? = null,

    @Json(name = "fee")
    val fee: FeeResponse? = null,

    /* Warning: Signature must be with SignatureType! */
    @Json(name = "signature")
    val signature: kotlin.String? = null

)

