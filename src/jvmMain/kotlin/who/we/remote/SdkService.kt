package who.we.remote

import io.ktor.client.statement.*
import who.we.remote.model.*

interface SdkService {

    suspend fun buildTransaction(request: BalanceTransferBody, seed: String): UnsignedTxPayloadResponse

    suspend fun signTransaction(request: UnsignedTxPayloadBody, seed: String): SignResponse

    suspend fun submitAndWatchTransaction(request: SubmitTxBody, seed: String): SubmitResultResponse

    suspend fun getExtrinsic(transactionHash: String, seed: String): ExtrinsicResultResponse

}