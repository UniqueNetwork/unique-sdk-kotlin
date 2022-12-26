package network.unique.service.impl

import network.unique.api.BalanceApi
import network.unique.model.AllBalancesResponse
import network.unique.model.TransferBody
import network.unique.service.BalanceService
import network.unique.service.MutationService
import network.unique.service.impl.balance.TransferMutationServiceImpl

class BalanceServiceImpl(basePath: String) : BalanceService {

    private val api: BalanceApi = BalanceApi(basePath)

    private val transferMutationService: MutationService<TransferBody> = TransferMutationServiceImpl(basePath)

    override fun getBalance(address: String): AllBalancesResponse {
        return api.balanceControllerGetBalance(address)
    }

    override fun getTransfer(): MutationService<TransferBody> {
        return transferMutationService
    }

}