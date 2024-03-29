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

import network.unique.model.PropertyPermission

import com.squareup.moshi.Json

/**
 * 
 *
 * @param key 
 * @param permission 
 */


data class PropertyKeyPermission (

    @Json(name = "key")
    val key: kotlin.String? = null,

    @Json(name = "permission")
    val permission: PropertyPermission? = null,

)

