package network.unique.service.impl

import network.unique.api.BalanceApi
import network.unique.model.AllBalancesResponse
import network.unique.service.BalanceService

class BalanceServiceImpl(basePath: String) : BalanceService {

    private val api: BalanceApi = BalanceApi(basePath)

    override fun getBalance(address: String): AllBalancesResponse {
        return api.balanceControllerGetBalance(address)
    }

}