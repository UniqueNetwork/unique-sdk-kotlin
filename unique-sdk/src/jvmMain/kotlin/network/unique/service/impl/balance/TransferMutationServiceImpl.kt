package network.unique.service.impl.balance

import network.unique.api.BalanceApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class TransferMutationServiceImpl(basePath: String) : MutationService<TransferMutationRequest, TransferMutationDefaultResponse>() {

    private val api: BalanceApi = BalanceApi(basePath)

    override fun build(args: TransferMutationRequest): UnsignedTxPayloadResponse {
        val res = api.transferMutation(args, BalanceApi.Use_transferMutation.build)
        return UnsignedTxPayloadResponse(
            res.signerPayloadJSON!!,
            res.signerPayloadRaw!!,
            res.signerPayloadHex!!,
            res.fee
        )
    }

    override fun getFee(args: TransferMutationRequest): FeeResponse {
        val res = api.transferMutation(args, BalanceApi.Use_transferMutation.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.transferMutation(
            TransferMutationRequest(
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
            TransferMutationRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), BalanceApi.Use_transferMutation.build, true
        )
        return res.fee!!
    }

    override fun sign(args: TransferMutationRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: TransferMutationRequest): TransferMutationDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): TransferMutationDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): TransferMutationDefaultResponse {
        return api.transferMutation(
            TransferMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), BalanceApi.Use_transferMutation.submit
        )
    }

    override fun submitWatch(args: TransferMutationRequest): TransferMutationDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): TransferMutationDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): TransferMutationDefaultResponse {
        return api.transferMutation(
            TransferMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), BalanceApi.Use_transferMutation.submit
        )
    }
}