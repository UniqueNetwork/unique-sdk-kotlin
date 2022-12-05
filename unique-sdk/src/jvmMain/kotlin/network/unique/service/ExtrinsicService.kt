package network.unique.service

import network.unique.model.*

interface ExtrinsicService {

    fun buildTx(body: TxBuildBody): UnsignedTxPayloadResponse

    fun signTx(body: UnsignedTxPayloadBody, seed: String): SignTxResultResponse

    fun verifySign(body: SubmitTxBody): VerificationResultResponse

    fun submit(body: SubmitTxBody): SubmitResultResponse

    fun calculateFee(body: ExtrinsicsControllerCalculateFeeRequest): FeeResponse

    fun getExtrinsicStatus(hash: String): ExtrinsicResultResponse

    fun getExtrinsic(blockHashOrNumber: String, extrinsicHash: String): GetExtrinsicResponse

}