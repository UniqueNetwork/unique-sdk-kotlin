package network.unique.service

import network.unique.model.AllBalancesResponse

interface BalanceService {

    fun getBalance(address: String): AllBalancesResponse

}