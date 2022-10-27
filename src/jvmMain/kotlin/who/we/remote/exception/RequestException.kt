package who.we.remote.exception

import io.ktor.client.statement.*

class RequestException(val response: HttpResponse, message: String): RuntimeException(message)