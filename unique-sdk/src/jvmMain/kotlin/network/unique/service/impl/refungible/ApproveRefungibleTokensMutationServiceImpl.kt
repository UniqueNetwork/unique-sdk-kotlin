package network.unique.service.impl.refungible

import network.unique.api.RefungibleApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class ApproveRefungibleTokensMutationServiceImpl(basePath: String) :
    MutationService<ApproveTokensMutationRequest1, ApproveTokensMutationDefaultResponse1>() {

    private val api: RefungibleApi = RefungibleApi(basePath)

    override fun build(args: ApproveTokensMutationRequest1): UnsignedTxPayloadResponse {
        val res = api.approveTokensMutation(args, RefungibleApi.Use_approveTokensMutation.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: ApproveTokensMutationRequest1): FeeResponse {
        val res = api.approveTokensMutation(args, RefungibleApi.Use_approveTokensMutation.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.approveTokensMutation(
            ApproveTokensMutationRequest1(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), RefungibleApi.Use_approveTokensMutation.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.approveTokensMutation(
            ApproveTokensMutationRequest1(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), RefungibleApi.Use_approveTokensMutation.build, true
        )
        return res.fee!!
    }

    override fun sign(args: ApproveTokensMutationRequest1): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: ApproveTokensMutationRequest1): ApproveTokensMutationDefaultResponse1 {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): ApproveTokensMutationDefaultResponse1 {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): ApproveTokensMutationDefaultResponse1 {
        return api.approveTokensMutation(
            ApproveTokensMutationRequest1(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), RefungibleApi.Use_approveTokensMutation.submit
        )
    }

    override fun submitWatch(args: ApproveTokensMutationRequest1): ApproveTokensMutationDefaultResponse1 {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): ApproveTokensMutationDefaultResponse1 {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): ApproveTokensMutationDefaultResponse1 {
        return api.approveTokensMutation(
            ApproveTokensMutationRequest1(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), RefungibleApi.Use_approveTokensMutation.submit
        )
    }

}