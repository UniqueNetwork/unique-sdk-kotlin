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
 * @param endpoint 
 * @param module 
 * @param method 
 */


data class ApiGetterParams (

    @Json(name = "endpoint")
    val endpoint: kotlin.String,

    @Json(name = "module")
    val module: kotlin.String,

    @Json(name = "method")
    val method: kotlin.String

)
