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
 * @param cid Entry CID_V0
 * @param fileUrl IPFS gateway file URL (renamed to fullUrl)
 * @param fullUrl Full url for entry on IPFS gateway
 */


data class IpfsUploadResponse (

    /* Entry CID_V0 */
    @Json(name = "cid")
    val cid: kotlin.String,

    /* IPFS gateway file URL (renamed to fullUrl) */
    @Json(name = "fileUrl")
    @Deprecated(message = "This property is deprecated.")
    val fileUrl: kotlin.String? = null,

    /* Full url for entry on IPFS gateway */
    @Json(name = "fullUrl")
    val fullUrl: kotlin.String? = null

)

