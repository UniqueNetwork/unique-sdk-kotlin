package network.unique.exception

import okhttp3.Response

class RequestException(val response: Response, message: String): RuntimeException(message)