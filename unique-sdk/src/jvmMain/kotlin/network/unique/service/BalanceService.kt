package network.unique.service

import network.unique.model.AllBalancesResponse
import network.unique.model.TransferBody
import network.unique.model.TransferMutationRequest

interface BalanceService {

    fun getBalance(address: String): AllBalancesResponse

    fun getTransfer(): MutationService<TransferMutationRequest>

}