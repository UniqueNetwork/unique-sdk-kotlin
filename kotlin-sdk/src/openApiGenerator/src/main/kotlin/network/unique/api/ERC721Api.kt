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

package network.unique.api

import java.io.IOException
import okhttp3.OkHttpClient
import okhttp3.HttpUrl

import network.unique.model.CreateERC721CollectionBody
import network.unique.model.CreateERC721TokenBody
import network.unique.model.ERC721ControllerCreateCollectionDefaultResponse
import network.unique.model.ERC721ControllerCreateTokenDefaultResponse

import com.squareup.moshi.Json

import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.infrastructure.ApiResponse
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.infrastructure.ClientError
import org.openapitools.client.infrastructure.ServerException
import org.openapitools.client.infrastructure.ServerError
import org.openapitools.client.infrastructure.MultiValueMap
import org.openapitools.client.infrastructure.PartConfig
import org.openapitools.client.infrastructure.RequestConfig
import org.openapitools.client.infrastructure.RequestMethod
import org.openapitools.client.infrastructure.ResponseType
import org.openapitools.client.infrastructure.Success
import org.openapitools.client.infrastructure.toMultiValue

class ERC721Api(basePath: kotlin.String = defaultBasePath, client: OkHttpClient = ApiClient.defaultClient) : ApiClient(basePath, client) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty(ApiClient.baseUrlKey, "http://localhost")
        }
    }

    /**
     * enum for parameter use
     */
     enum class Use_eRC721ControllerCreateCollection(val value: kotlin.String) {
         @Json(name = "Build") build("Build"),
         @Json(name = "BuildBatch") buildBatch("BuildBatch"),
         @Json(name = "Sign") sign("Sign"),
         @Json(name = "Submit") submit("Submit"),
         @Json(name = "SubmitWatch") submitWatch("SubmitWatch"),
         @Json(name = "Result") result("Result")
     }

    /**
     * 
     * 
     * @param createERC721CollectionBody 
     * @param use  (optional)
     * @param withFee  (optional, default to false)
     * @param verify  (optional, default to false)
     * @param callbackUrl  (optional)
     * @param nonce  (optional)
     * @return ERC721ControllerCreateCollectionDefaultResponse
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun eRC721ControllerCreateCollection(createERC721CollectionBody: CreateERC721CollectionBody, use: Use_eRC721ControllerCreateCollection? = null, withFee: kotlin.Boolean? = false, verify: kotlin.Boolean? = false, callbackUrl: kotlin.String? = null, nonce: java.math.BigDecimal? = null) : ERC721ControllerCreateCollectionDefaultResponse {
        val localVarResponse = eRC721ControllerCreateCollectionWithHttpInfo(createERC721CollectionBody = createERC721CollectionBody, use = use, withFee = withFee, verify = verify, callbackUrl = callbackUrl, nonce = nonce)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as ERC721ControllerCreateCollectionDefaultResponse
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
     * 
     * 
     * @param createERC721CollectionBody 
     * @param use  (optional)
     * @param withFee  (optional, default to false)
     * @param verify  (optional, default to false)
     * @param callbackUrl  (optional)
     * @param nonce  (optional)
     * @return ApiResponse<ERC721ControllerCreateCollectionDefaultResponse?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun eRC721ControllerCreateCollectionWithHttpInfo(createERC721CollectionBody: CreateERC721CollectionBody, use: Use_eRC721ControllerCreateCollection?, withFee: kotlin.Boolean?, verify: kotlin.Boolean?, callbackUrl: kotlin.String?, nonce: java.math.BigDecimal?) : ApiResponse<ERC721ControllerCreateCollectionDefaultResponse?> {
        val localVariableConfig = eRC721ControllerCreateCollectionRequestConfig(createERC721CollectionBody = createERC721CollectionBody, use = use, withFee = withFee, verify = verify, callbackUrl = callbackUrl, nonce = nonce)

        return request<CreateERC721CollectionBody, ERC721ControllerCreateCollectionDefaultResponse>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation eRC721ControllerCreateCollection
     *
     * @param createERC721CollectionBody 
     * @param use  (optional)
     * @param withFee  (optional, default to false)
     * @param verify  (optional, default to false)
     * @param callbackUrl  (optional)
     * @param nonce  (optional)
     * @return RequestConfig
     */
    fun eRC721ControllerCreateCollectionRequestConfig(createERC721CollectionBody: CreateERC721CollectionBody, use: Use_eRC721ControllerCreateCollection?, withFee: kotlin.Boolean?, verify: kotlin.Boolean?, callbackUrl: kotlin.String?, nonce: java.math.BigDecimal?) : RequestConfig<CreateERC721CollectionBody> {
        val localVariableBody = createERC721CollectionBody
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (use != null) {
                    put("use", listOf(use.toString()))
                }
                if (withFee != null) {
                    put("withFee", listOf(withFee.toString()))
                }
                if (verify != null) {
                    put("verify", listOf(verify.toString()))
                }
                if (callbackUrl != null) {
                    put("callbackUrl", listOf(callbackUrl.toString()))
                }
                if (nonce != null) {
                    put("nonce", listOf(nonce.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/v1/erc721/collection",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
     * enum for parameter use
     */
     enum class Use_eRC721ControllerCreateToken(val value: kotlin.String) {
         @Json(name = "Build") build("Build"),
         @Json(name = "BuildBatch") buildBatch("BuildBatch"),
         @Json(name = "Sign") sign("Sign"),
         @Json(name = "Submit") submit("Submit"),
         @Json(name = "SubmitWatch") submitWatch("SubmitWatch"),
         @Json(name = "Result") result("Result")
     }

    /**
     * 
     * 
     * @param createERC721TokenBody 
     * @param use  (optional)
     * @param withFee  (optional, default to false)
     * @param verify  (optional, default to false)
     * @param callbackUrl  (optional)
     * @param nonce  (optional)
     * @return ERC721ControllerCreateTokenDefaultResponse
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun eRC721ControllerCreateToken(createERC721TokenBody: CreateERC721TokenBody, use: Use_eRC721ControllerCreateToken? = null, withFee: kotlin.Boolean? = false, verify: kotlin.Boolean? = false, callbackUrl: kotlin.String? = null, nonce: java.math.BigDecimal? = null) : ERC721ControllerCreateTokenDefaultResponse {
        val localVarResponse = eRC721ControllerCreateTokenWithHttpInfo(createERC721TokenBody = createERC721TokenBody, use = use, withFee = withFee, verify = verify, callbackUrl = callbackUrl, nonce = nonce)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as ERC721ControllerCreateTokenDefaultResponse
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
     * 
     * 
     * @param createERC721TokenBody 
     * @param use  (optional)
     * @param withFee  (optional, default to false)
     * @param verify  (optional, default to false)
     * @param callbackUrl  (optional)
     * @param nonce  (optional)
     * @return ApiResponse<ERC721ControllerCreateTokenDefaultResponse?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun eRC721ControllerCreateTokenWithHttpInfo(createERC721TokenBody: CreateERC721TokenBody, use: Use_eRC721ControllerCreateToken?, withFee: kotlin.Boolean?, verify: kotlin.Boolean?, callbackUrl: kotlin.String?, nonce: java.math.BigDecimal?) : ApiResponse<ERC721ControllerCreateTokenDefaultResponse?> {
        val localVariableConfig = eRC721ControllerCreateTokenRequestConfig(createERC721TokenBody = createERC721TokenBody, use = use, withFee = withFee, verify = verify, callbackUrl = callbackUrl, nonce = nonce)

        return request<CreateERC721TokenBody, ERC721ControllerCreateTokenDefaultResponse>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation eRC721ControllerCreateToken
     *
     * @param createERC721TokenBody 
     * @param use  (optional)
     * @param withFee  (optional, default to false)
     * @param verify  (optional, default to false)
     * @param callbackUrl  (optional)
     * @param nonce  (optional)
     * @return RequestConfig
     */
    fun eRC721ControllerCreateTokenRequestConfig(createERC721TokenBody: CreateERC721TokenBody, use: Use_eRC721ControllerCreateToken?, withFee: kotlin.Boolean?, verify: kotlin.Boolean?, callbackUrl: kotlin.String?, nonce: java.math.BigDecimal?) : RequestConfig<CreateERC721TokenBody> {
        val localVariableBody = createERC721TokenBody
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (use != null) {
                    put("use", listOf(use.toString()))
                }
                if (withFee != null) {
                    put("withFee", listOf(withFee.toString()))
                }
                if (verify != null) {
                    put("verify", listOf(verify.toString()))
                }
                if (callbackUrl != null) {
                    put("callbackUrl", listOf(callbackUrl.toString()))
                }
                if (nonce != null) {
                    put("nonce", listOf(nonce.toString()))
                }
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/v1/erc721/token",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }


    private fun encodeURIComponent(uriComponent: kotlin.String): kotlin.String =
        HttpUrl.Builder().scheme("http").host("localhost").addPathSegment(uriComponent).build().encodedPathSegments[0]
}
