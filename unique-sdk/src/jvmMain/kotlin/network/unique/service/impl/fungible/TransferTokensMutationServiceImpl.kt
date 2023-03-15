package network.unique.service.impl.fungible

import network.unique.api.FungibleApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class TransferTokensMutationServiceImpl(basePath: String) :
    MutationService<TransferTokensMutationRequest, TransferTokensMutationDefaultResponse>() {

    private val api: FungibleApi = FungibleApi(basePath)

    override fun build(args: TransferTokensMutationRequest): UnsignedTxPayloadResponse {
        val res = api.transferTokensMutation(args, FungibleApi.Use_transferTokensMutation.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: TransferTokensMutationRequest): FeeResponse {
        val res = api.transferTokensMutation(args, FungibleApi.Use_transferTokensMutation.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.transferTokensMutation(
            TransferTokensMutationRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), FungibleApi.Use_transferTokensMutation.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.transferTokensMutation(
            TransferTokensMutationRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), FungibleApi.Use_transferTokensMutation.build, true
        )
        return res.fee!!
    }

    override fun sign(args: TransferTokensMutationRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw!!.data!!)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: TransferTokensMutationRequest): TransferTokensMutationDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): TransferTokensMutationDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): TransferTokensMutationDefaultResponse {
        return api.transferTokensMutation(
            TransferTokensMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), FungibleApi.Use_transferTokensMutation.submit
        )
    }

    override fun submitWatch(args: TransferTokensMutationRequest): TransferTokensMutationDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): TransferTokensMutationDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): TransferTokensMutationDefaultResponse {
        return api.transferTokensMutation(
            TransferTokensMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), FungibleApi.Use_transferTokensMutation.submit
        )
    }

}