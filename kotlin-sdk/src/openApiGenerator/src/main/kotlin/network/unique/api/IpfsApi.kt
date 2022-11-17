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

import network.unique.model.IpfsUploadResponse

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

class IpfsApi(basePath: kotlin.String = defaultBasePath, client: OkHttpClient = ApiClient.defaultClient) : ApiClient(basePath, client) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty(ApiClient.baseUrlKey, "http://localhost")
        }
    }

    /**
     * 
     * 
     * @param files  (optional)
     * @param cid  (optional)
     * @return IpfsUploadResponse
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun ipfsControllerAddFiles(files: kotlin.collections.List<java.io.File>? = null, cid: kotlin.String? = null) : IpfsUploadResponse {
        val localVarResponse = ipfsControllerAddFilesWithHttpInfo(files = files, cid = cid)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as IpfsUploadResponse
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
     * @param files  (optional)
     * @param cid  (optional)
     * @return ApiResponse<IpfsUploadResponse?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun ipfsControllerAddFilesWithHttpInfo(files: kotlin.collections.List<java.io.File>?, cid: kotlin.String?) : ApiResponse<IpfsUploadResponse?> {
        val localVariableConfig = ipfsControllerAddFilesRequestConfig(files = files, cid = cid)

        return request<Map<String, PartConfig<*>>, IpfsUploadResponse>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation ipfsControllerAddFiles
     *
     * @param files  (optional)
     * @param cid  (optional)
     * @return RequestConfig
     */
    fun ipfsControllerAddFilesRequestConfig(files: kotlin.collections.List<java.io.File>?, cid: kotlin.String?) : RequestConfig<Map<String, PartConfig<*>>> {
        val localVariableBody = mapOf(
            "files" to PartConfig(body = files, headers = mutableMapOf()),
            "cid" to PartConfig(body = cid, headers = mutableMapOf()),)
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf("Content-Type" to "multipart/form-data")
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.PATCH,
            path = "/v1/ipfs/add-files",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
     * 
     * 
     * @param file  (optional)
     * @return IpfsUploadResponse
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun ipfsControllerUploadFile(file: java.io.File? = null) : IpfsUploadResponse {
        val localVarResponse = ipfsControllerUploadFileWithHttpInfo(file = file)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as IpfsUploadResponse
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
     * @param file  (optional)
     * @return ApiResponse<IpfsUploadResponse?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun ipfsControllerUploadFileWithHttpInfo(file: java.io.File?) : ApiResponse<IpfsUploadResponse?> {
        val localVariableConfig = ipfsControllerUploadFileRequestConfig(file = file)

        return request<Map<String, PartConfig<*>>, IpfsUploadResponse>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation ipfsControllerUploadFile
     *
     * @param file  (optional)
     * @return RequestConfig
     */
    fun ipfsControllerUploadFileRequestConfig(file: java.io.File?) : RequestConfig<Map<String, PartConfig<*>>> {
        val localVariableBody = mapOf(
            "file" to PartConfig(body = file, headers = mutableMapOf()),)
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf("Content-Type" to "multipart/form-data")
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/v1/ipfs/upload-file",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
     * 
     * 
     * @param files  (optional)
     * @return IpfsUploadResponse
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun ipfsControllerUploadFiles(files: kotlin.collections.List<java.io.File>? = null) : IpfsUploadResponse {
        val localVarResponse = ipfsControllerUploadFilesWithHttpInfo(files = files)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as IpfsUploadResponse
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
     * @param files  (optional)
     * @return ApiResponse<IpfsUploadResponse?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun ipfsControllerUploadFilesWithHttpInfo(files: kotlin.collections.List<java.io.File>?) : ApiResponse<IpfsUploadResponse?> {
        val localVariableConfig = ipfsControllerUploadFilesRequestConfig(files = files)

        return request<Map<String, PartConfig<*>>, IpfsUploadResponse>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation ipfsControllerUploadFiles
     *
     * @param files  (optional)
     * @return RequestConfig
     */
    fun ipfsControllerUploadFilesRequestConfig(files: kotlin.collections.List<java.io.File>?) : RequestConfig<Map<String, PartConfig<*>>> {
        val localVariableBody = mapOf(
            "files" to PartConfig(body = files, headers = mutableMapOf()),)
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf("Content-Type" to "multipart/form-data")
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/v1/ipfs/upload-files",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
     * 
     * 
     * @param file  (optional)
     * @return IpfsUploadResponse
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    @Deprecated(message = "This operation is deprecated.")
    fun ipfsControllerUploadZip(file: java.io.File? = null) : IpfsUploadResponse {
        @Suppress("DEPRECATION")
        val localVarResponse = ipfsControllerUploadZipWithHttpInfo(file = file)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as IpfsUploadResponse
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
     * @param file  (optional)
     * @return ApiResponse<IpfsUploadResponse?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    @Deprecated(message = "This operation is deprecated.")
    fun ipfsControllerUploadZipWithHttpInfo(file: java.io.File?) : ApiResponse<IpfsUploadResponse?> {
        @Suppress("DEPRECATION")
        val localVariableConfig = ipfsControllerUploadZipRequestConfig(file = file)

        return request<Map<String, PartConfig<*>>, IpfsUploadResponse>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation ipfsControllerUploadZip
     *
     * @param file  (optional)
     * @return RequestConfig
     */
    @Deprecated(message = "This operation is deprecated.")
    fun ipfsControllerUploadZipRequestConfig(file: java.io.File?) : RequestConfig<Map<String, PartConfig<*>>> {
        val localVariableBody = mapOf(
            "file" to PartConfig(body = file, headers = mutableMapOf()),)
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf("Content-Type" to "multipart/form-data")
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/v1/ipfs/upload-zip",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }


    private fun encodeURIComponent(uriComponent: kotlin.String): kotlin.String =
        HttpUrl.Builder().scheme("http").host("localhost").addPathSegment(uriComponent).build().encodedPathSegments[0]
}
