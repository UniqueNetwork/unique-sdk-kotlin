package network.unique.service

import network.unique.model.ChainPropertiesResponse
import network.unique.model.GetNonceResponse

interface CommonService {

    fun getNonce(address: String): GetNonceResponse

    fun getChainProperties(): ChainPropertiesResponse

}