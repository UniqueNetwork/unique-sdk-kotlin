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

import network.unique.model.AccountDataResponse
import network.unique.model.GenerateAccountDataBody

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

class AccountApi(basePath: kotlin.String = defaultBasePath, client: OkHttpClient = ApiClient.defaultClient) : ApiClient(basePath, client) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty(ApiClient.baseUrlKey, "http://localhost")
        }
    }

    /**
     * 
     * Create mnemonic string using BIP39. Create valid Substrate-compatible seed from mnemonic. Generate new public key from the seed
     * @param generateAccountDataBody 
     * @return AccountDataResponse
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun accountControllerGenerate(generateAccountDataBody: GenerateAccountDataBody) : AccountDataResponse {
        val localVarResponse = accountControllerGenerateWithHttpInfo(generateAccountDataBody = generateAccountDataBody)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as AccountDataResponse
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
     * Create mnemonic string using BIP39. Create valid Substrate-compatible seed from mnemonic. Generate new public key from the seed
     * @param generateAccountDataBody 
     * @return ApiResponse<AccountDataResponse?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun accountControllerGenerateWithHttpInfo(generateAccountDataBody: GenerateAccountDataBody) : ApiResponse<AccountDataResponse?> {
        val localVariableConfig = accountControllerGenerateRequestConfig(generateAccountDataBody = generateAccountDataBody)

        return request<GenerateAccountDataBody, AccountDataResponse>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation accountControllerGenerate
     *
     * @param generateAccountDataBody 
     * @return RequestConfig
     */
    fun accountControllerGenerateRequestConfig(generateAccountDataBody: GenerateAccountDataBody) : RequestConfig<GenerateAccountDataBody> {
        val localVariableBody = generateAccountDataBody
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/v1/account/generate",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
     * enum for parameter pairType
     */
     enum class PairType_accountControllerGetAccount(val value: kotlin.String) {
         @Json(name = "sr25519") sr25519("sr25519"),
         @Json(name = "ed25519") ed25519("ed25519"),
         @Json(name = "ecdsa") ecdsa("ecdsa"),
         @Json(name = "ethereum") ethereum("ethereum")
     }

    /**
     * 
     * Create valid Substrate-compatible seed from mnemonic. Generate new public key from the seed
     * @param mnemonic The mnemonic seed gives full access to your account
     * @param pairType Signature: ed25519, sr25519 implementation using Schnorr signatures. ECDSA signatures on the secp256k1 curve (optional)
     * @param meta A metadata argument that contains account information (that may be obtained from the json file of an account backup) (optional)
     * @return AccountDataResponse
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun accountControllerGetAccount(mnemonic: kotlin.String, pairType: PairType_accountControllerGetAccount? = null, meta: kotlin.Any? = null) : AccountDataResponse {
        val localVarResponse = accountControllerGetAccountWithHttpInfo(mnemonic = mnemonic, pairType = pairType, meta = meta)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as AccountDataResponse
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
     * Create valid Substrate-compatible seed from mnemonic. Generate new public key from the seed
     * @param mnemonic The mnemonic seed gives full access to your account
     * @param pairType Signature: ed25519, sr25519 implementation using Schnorr signatures. ECDSA signatures on the secp256k1 curve (optional)
     * @param meta A metadata argument that contains account information (that may be obtained from the json file of an account backup) (optional)
     * @return ApiResponse<AccountDataResponse?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun accountControllerGetAccountWithHttpInfo(mnemonic: kotlin.String, pairType: PairType_accountControllerGetAccount?, meta: kotlin.Any?) : ApiResponse<AccountDataResponse?> {
        val localVariableConfig = accountControllerGetAccountRequestConfig(mnemonic = mnemonic, pairType = pairType, meta = meta)

        return request<Unit, AccountDataResponse>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation accountControllerGetAccount
     *
     * @param mnemonic The mnemonic seed gives full access to your account
     * @param pairType Signature: ed25519, sr25519 implementation using Schnorr signatures. ECDSA signatures on the secp256k1 curve (optional)
     * @param meta A metadata argument that contains account information (that may be obtained from the json file of an account backup) (optional)
     * @return RequestConfig
     */
    fun accountControllerGetAccountRequestConfig(mnemonic: kotlin.String, pairType: PairType_accountControllerGetAccount?, meta: kotlin.Any?) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>()
            .apply {
                if (pairType != null) {
                    put("pairType", listOf(pairType.toString()))
                }
                if (meta != null) {
                    put("meta", listOf(meta.toString()))
                }
                put("mnemonic", listOf(mnemonic.toString()))
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/v1/account",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }


    private fun encodeURIComponent(uriComponent: kotlin.String): kotlin.String =
        HttpUrl.Builder().scheme("http").host("localhost").addPathSegment(uriComponent).build().encodedPathSegments[0]
}