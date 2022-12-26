package network.unique.service

import network.unique.model.AllBalancesResponse
import network.unique.model.TransferBody

interface BalanceService {

    fun getBalance(address: String): AllBalancesResponse

    fun getTransfer(): MutationService<TransferBody>

}