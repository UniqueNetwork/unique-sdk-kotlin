package network.unique.service.impl

import io.ktor.client.*
import io.ktor.client.statement.*
import io.ktor.http.*
import network.unique.client.KtorClientWrapper
import network.unique.exception.RequestException

abstract class BasicHttpService(clientWrapper: KtorClientWrapper) {

    protected val client: HttpClient = clientWrapper.client;
    protected val host: String = clientWrapper.host;
    protected val protocol: URLProtocol = clientWrapper.protocol;

    protected fun validateResponse(response: HttpResponse) {
        val statusCodeType = response.status.value / 100

        if (statusCodeType != 2 && statusCodeType != 3) {
            throw RequestException(response, "Request error")
        }
    }

}