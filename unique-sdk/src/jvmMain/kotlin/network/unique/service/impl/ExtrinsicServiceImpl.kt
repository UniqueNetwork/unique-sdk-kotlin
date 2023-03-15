package network.unique.service.impl

import network.unique.api.ExtrinsicApi
import network.unique.model.*
import network.unique.service.ExtrinsicService
import network.unique.signer.CryptoScheme
import network.unique.signer.Pair

class ExtrinsicServiceImpl(basePath: String) : ExtrinsicService {

    private val api: ExtrinsicApi = ExtrinsicApi(basePath)

    override fun buildTx(body: TxBuildBody): UnsignedTxPayloadResponse {
        return api.extrinsicsControllerBuildTx(body)
    }

    override fun signTx(body: UnsignedTxPayloadBody, seed: String): SignTxResultResponse {
        val keyPair = Pair.fromSuri(CryptoScheme.Sr25519, seed, null)

        val signature = keyPair.sign(toByteArray(body.signerPayloadRaw!!.data!!.substring(2)))
            .joinToString("") { eachByte -> "%02x".format(eachByte) }

        return SignTxResultResponse("0x01$signature", SignTxResultResponse.SignatureType.sr25519)
    }

    override fun verifySign(body: SubmitTxBody): VerificationResultResponse {
        return api.extrinsicsControllerVerifySign(body)
    }

    override fun submit(body: SubmitTxBody): SubmitResultResponse {
        return api.extrinsicsControllerSubmitTx(body)
    }

    override fun calculateFee(body: ExtrinsicsControllerCalculateFeeRequest): FeeResponse {
        return api.extrinsicsControllerCalculateFee(body)
    }

    override fun getExtrinsicStatus(hash: String): ExtrinsicResultResponse {
        return api.extrinsicsControllerGetStatus(hash)
    }

    override fun getExtrinsic(blockHashOrNumber: String, extrinsicHash: String): GetExtrinsicResponse {
        return api.extrinsicsControllerGet(blockHashOrNumber, extrinsicHash)
    }

    private fun toByteArray(data: String): ByteArray {
        return data.chunked(2)
            .map { it.toInt(16).toByte() }
            .toByteArray()
    }

}