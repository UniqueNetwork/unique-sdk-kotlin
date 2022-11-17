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

import network.unique.model.EvmSendArgumentsDtoGasLimit
import network.unique.model.EvmSendArgumentsDtoMaxFeePerGas
import network.unique.model.EvmSendArgumentsDtoValue

import com.squareup.moshi.Json

/**
 * 
 *
 * @param address The ss-58 encoded address
 * @param abi JSON ABI from your smart contract
 * @param contractAddress Ethereum address of your smart contract
 * @param funcName Name of function smart-contract
 * @param args An array of arguments you want to pass to the function call
 * @param nonce 
 * @param `value` 
 * @param gasLimit 
 * @param maxFeePerGas 
 * @param maxPriorityFeePerGas 
 */


data class EvmSendArgumentsDto (

    /* The ss-58 encoded address */
    @Json(name = "address")
    val address: kotlin.String,

    /* JSON ABI from your smart contract */
    @Json(name = "abi")
    val abi: kotlin.collections.List<kotlin.Any>,

    /* Ethereum address of your smart contract */
    @Json(name = "contractAddress")
    val contractAddress: kotlin.String,

    /* Name of function smart-contract */
    @Json(name = "funcName")
    val funcName: kotlin.String,

    /* An array of arguments you want to pass to the function call */
    @Json(name = "args")
    val args: kotlin.collections.List<kotlin.Any>? = null,

    @Json(name = "nonce")
    val nonce: java.math.BigDecimal? = null,

    @Json(name = "value")
    val `value`: EvmSendArgumentsDtoValue? = null,

    @Json(name = "gasLimit")
    val gasLimit: EvmSendArgumentsDtoGasLimit? = null,

    @Json(name = "maxFeePerGas")
    val maxFeePerGas: EvmSendArgumentsDtoMaxFeePerGas? = null,

    @Json(name = "maxPriorityFeePerGas")
    val maxPriorityFeePerGas: EvmSendArgumentsDtoMaxFeePerGas? = null

)

