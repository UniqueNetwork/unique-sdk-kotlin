package network.unique.remote

import io.ktor.client.statement.*
import network.unique.remote.model.*
import who.we.remote.model.*

interface SdkService {

    suspend fun buildTransaction(request: BalanceTransferBody, seed: String): UnsignedTxPayloadResponse

    suspend fun signTransaction(request: UnsignedTxPayloadBody, seed: String): SignResponse

    suspend fun submitAndWatchTransaction(request: SubmitTxBody, seed: String): SubmitResultResponse

    suspend fun getExtrinsic(transactionHash: String, seed: String): ExtrinsicResultResponse

}