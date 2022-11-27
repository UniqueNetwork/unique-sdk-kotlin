package network.unique.service.impl

import network.unique.api.CommonApi
import network.unique.model.ChainPropertiesResponse
import network.unique.model.GetNonceResponse
import network.unique.service.CommonService

class CommonServiceImpl(basePath: String) : CommonService {

    private val api: CommonApi = CommonApi(basePath)

    override fun getNonce(address: String): GetNonceResponse {
        return api.commonControllerGetNonce(address)
    }

    override fun getChainProperties(): ChainPropertiesResponse {
        return api.commonControllerGetChainProperties()
    }
}