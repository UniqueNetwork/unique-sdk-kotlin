package network.unique.service.impl.token

import network.unique.api.TokensApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class ApproveTokenMutationServiceImpl(basePath: String) :
    MutationService<ApproveRequest, ApproveDefaultResponse>() {

    private val api: TokensApi = TokensApi(basePath)

    override fun build(args: ApproveRequest): UnsignedTxPayloadResponse {
        val res = api.approve(args, TokensApi.Use_approve.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: ApproveRequest): FeeResponse {
        val res = api.approve(args, TokensApi.Use_approve.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.approve(
            ApproveRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), TokensApi.Use_approve.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.approve(
            ApproveRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), TokensApi.Use_approve.build, true
        )
        return res.fee!!
    }

    override fun sign(args: ApproveRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw!!.data!!)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: ApproveRequest): ApproveDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): ApproveDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): ApproveDefaultResponse {
        return api.approve(
            ApproveRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_approve.submit
        )
    }

    override fun submitWatch(args: ApproveRequest): ApproveDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): ApproveDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): ApproveDefaultResponse {
        return api.approve(
            ApproveRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_approve.submit
        )
    }

}