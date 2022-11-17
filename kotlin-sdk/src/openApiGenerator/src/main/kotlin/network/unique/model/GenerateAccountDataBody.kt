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
 * @param pairType Signature: ed25519, sr25519 implementation using Schnorr signatures. ECDSA signatures on the secp256k1 curve
 * @param meta A metadata argument that contains account information (that may be obtained from the json file of an account backup)
 */


data class GenerateAccountDataBody (

    /* Signature: ed25519, sr25519 implementation using Schnorr signatures. ECDSA signatures on the secp256k1 curve */
    @Json(name = "pairType")
    val pairType: GenerateAccountDataBody.PairType? = null,

    /* A metadata argument that contains account information (that may be obtained from the json file of an account backup) */
    @Json(name = "meta")
    val meta: kotlin.Any? = null

) {

    /**
     * Signature: ed25519, sr25519 implementation using Schnorr signatures. ECDSA signatures on the secp256k1 curve
     *
     * Values: sr25519,ed25519,ecdsa,ethereum
     */
    enum class PairType(val value: kotlin.String) {
        @Json(name = "sr25519") sr25519("sr25519"),
        @Json(name = "ed25519") ed25519("ed25519"),
        @Json(name = "ecdsa") ecdsa("ecdsa"),
        @Json(name = "ethereum") ethereum("ethereum");
    }
}

