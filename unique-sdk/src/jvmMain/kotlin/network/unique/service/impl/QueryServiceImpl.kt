package network.unique.service.impl

import network.unique.api.QueryApi
import network.unique.model.ApiRequestBody
import network.unique.service.QueryService

class QueryServiceImpl(basePath: String) : QueryService {

    private val api: QueryApi = QueryApi(basePath)

    override fun getPolkadotParameter(endpoint: String, module: String, method: String): Any {
        return api.queryControllerGet(endpoint, module, method)
    }

    override fun callPolkadotMethod(endpoint: String, module: String, method: String, body: ApiRequestBody): Any {
        return api.queryControllerQuery(endpoint, module, method, body)
    }

}