package network.unique.service

import network.unique.model.*
import network.unique.model.extrinsic.*

interface ExtrinsicService {

    suspend fun buildExtrinsic(request: TxBuildBody): UnsignedTxPayloadResponse

    suspend fun signExtrinsic(request: UnsignedTxPayloadBody, seed: String): SignTxResultResponse

    suspend fun verifyExtrinsicSign(request: SubmitTxBody): VerificationResultResponse

    suspend fun submitExtrinsic(request: SubmitTxBody): SubmitResultResponse

    suspend fun calculateExtrinsicFee(request: Feeable): FeeResponse

    suspend fun getExtrinsicStatus(transactionHash: String): ExtrinsicResultResponse

    suspend fun getExtrinsic(blockHashOrNumber: String, extrinsicHash: String): GetExtrinsicResponse

}