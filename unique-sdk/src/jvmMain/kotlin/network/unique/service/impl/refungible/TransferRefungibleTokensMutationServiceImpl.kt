package network.unique.service.impl.refungible

import network.unique.api.RefungibleApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class TransferRefungibleTokensMutationServiceImpl(basePath: String) :
    MutationService<TransferTokensMutationRequest1, TransferTokensMutationDefaultResponse1>() {

    private val api: RefungibleApi = RefungibleApi(basePath)

    override fun build(args: TransferTokensMutationRequest1): UnsignedTxPayloadResponse {
        val res = api.transferTokensMutation(args, RefungibleApi.Use_transferTokensMutation.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: TransferTokensMutationRequest1): FeeResponse {
        val res = api.transferTokensMutation(args, RefungibleApi.Use_transferTokensMutation.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.transferTokensMutation(
            TransferTokensMutationRequest1(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), RefungibleApi.Use_transferTokensMutation.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.transferTokensMutation(
            TransferTokensMutationRequest1(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), RefungibleApi.Use_transferTokensMutation.build, true
        )
        return res.fee!!
    }

    override fun sign(args: TransferTokensMutationRequest1): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw!!.data!!)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: TransferTokensMutationRequest1): TransferTokensMutationDefaultResponse1 {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): TransferTokensMutationDefaultResponse1 {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): TransferTokensMutationDefaultResponse1 {
        return api.transferTokensMutation(
            TransferTokensMutationRequest1(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), RefungibleApi.Use_transferTokensMutation.submit
        )
    }

    override fun submitWatch(args: TransferTokensMutationRequest1): TransferTokensMutationDefaultResponse1 {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): TransferTokensMutationDefaultResponse1 {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): TransferTokensMutationDefaultResponse1 {
        return api.transferTokensMutation(
            TransferTokensMutationRequest1(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), RefungibleApi.Use_transferTokensMutation.submit
        )
    }

}