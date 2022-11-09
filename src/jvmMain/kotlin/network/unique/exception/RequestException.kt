package network.unique.exception

import io.ktor.client.statement.*

class RequestException(val response: HttpResponse, message: String): RuntimeException(message)