package network.unique.service

import network.unique.model.ApiRequestBody

interface QueryService {

    fun getPolkadotParameter(endpoint: String, module: String, method: String): Any

    fun callPolkadotMethod(endpoint: String, module: String, method: String, body: ApiRequestBody): Any

}