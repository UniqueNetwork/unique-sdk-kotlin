package network.unique.service.impl

import network.unique.api.BalanceApi
import network.unique.model.*
import network.unique.service.MutationService
import network.unique.signer.CryptoScheme
import network.unique.signer.Pair

class TransferService(basePath: String) : MutationService<BalanceTransferBody>() {

    private val api: BalanceApi = BalanceApi(basePath)

    override fun build(args: BalanceTransferBody): UnsignedTxPayloadResponse {
        val res = api.transferMutation(args, BalanceApi.Use_transferMutation.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: BalanceTransferBody): FeeResponse {
        val res = api.transferMutation(args, BalanceApi.Use_transferMutation.build, true)
        return res.fee!!
    }

    override fun sign(args: BalanceTransferBody, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val keyPair = Pair.fromSuri(CryptoScheme.Sr25519, seed, null)

        val signature = keyPair.sign(toByteArray(args.signerPayloadRaw.data.substring(2)))
            .joinToString("") { eachByte -> "%02x".format(eachByte) }

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: BalanceTransferBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return api.transferMutation(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): SubmitResultResponse {
        TODO("Not yet implemented")
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        TODO("Not yet implemented")
    }

    override fun submitWatch(args: BalanceTransferBody): SubmitResultResponse {
        TODO("Not yet implemented")
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): SubmitResultResponse {
        TODO("Not yet implemented")
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        TODO("Not yet implemented")
    }

    override fun submitWaitResult(args: BalanceTransferBody): ExtrinsicResultResponse {
        TODO("Not yet implemented")
    }

    override fun submitWaitResult(args: UnsignedTxPayloadResponse): ExtrinsicResultResponse {
        TODO("Not yet implemented")
    }

    override fun submitWaitResult(args: SubmitTxBody): ExtrinsicResultResponse {
        TODO("Not yet implemented")
    }
}