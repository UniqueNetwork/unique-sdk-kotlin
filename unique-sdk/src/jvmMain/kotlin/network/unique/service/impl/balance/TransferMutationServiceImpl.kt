package network.unique.service.impl.balance

import network.unique.api.BalanceApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class TransferMutationServiceImpl(basePath: String) : MutationService<TransferBody>() {

    private val api: BalanceApi = BalanceApi(basePath)

    override fun build(args: TransferBody): UnsignedTxPayloadResponse {
        val res = api.transferMutation(args, BalanceApi.Use_transferMutation.build)
        return UnsignedTxPayloadResponse(
            res.signerPayloadJSON!!,
            res.signerPayloadRaw!!,
            res.signerPayloadHex!!,
            res.fee
        )
    }

    override fun getFee(args: TransferBody): FeeResponse {
        val res = api.transferMutation(args, BalanceApi.Use_transferMutation.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.transferMutation(
            TransferBody(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), BalanceApi.Use_transferMutation.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.transferMutation(
            TransferBody(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), BalanceApi.Use_transferMutation.build, true
        )
        return res.fee!!
    }

    override fun sign(args: TransferBody, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: TransferBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.transferMutation(
            TransferBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), BalanceApi.Use_transferMutation.submit
        )
        return SubmitResultResponse(response.hash!!)
    }

    override fun submitWatch(args: TransferBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.transferMutation(
            TransferBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), BalanceApi.Use_transferMutation.submitWatch
        )
        return SubmitResultResponse(response.hash!!)
    }
}