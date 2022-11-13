package network.unique.service

import network.unique.model.SubmitResultResponse
import network.unique.model.SubmitTxBody
import network.unique.model.UnsignedTxPayloadBody
import network.unique.model.UnsignedTxPayloadResponse
import network.unique.model.balance.*

interface BalanceService {

    suspend fun getBalance(address: String): AllBalanceResponse

    suspend fun buildTransaction(request: BalanceTransferBody, seed: String): UnsignedTxPayloadResponse

    suspend fun signTransaction(request: UnsignedTxPayloadBody, seed: String): SignResponse

    suspend fun submitAndWatchTransaction(request: SubmitTxBody, seed: String): SubmitResultResponse

}