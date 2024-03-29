package network.unique.service.impl.token

import network.unique.api.TokensApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class CreateMultipleTokensMutationServiceImpl(basePath: String) :
    MutationService<CreateMultipleTokensMutationRequest, CreateNewTokenMutationDefaultResponse>() {

    private val api: TokensApi = TokensApi(basePath)

    override fun build(args: CreateMultipleTokensMutationRequest): UnsignedTxPayloadResponse {
        val res = api.createMultipleTokensMutation(args, TokensApi.Use_createMultipleTokensMutation.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: CreateMultipleTokensMutationRequest): FeeResponse {
        val res = api.createMultipleTokensMutation(args, TokensApi.Use_createMultipleTokensMutation.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.createMultipleTokensMutation(
            CreateMultipleTokensMutationRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), TokensApi.Use_createMultipleTokensMutation.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.createMultipleTokensMutation(
            CreateMultipleTokensMutationRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), TokensApi.Use_createMultipleTokensMutation.build, true
        )
        return res.fee!!
    }

    override fun sign(args: CreateMultipleTokensMutationRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw!!.data!!)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: CreateMultipleTokensMutationRequest): CreateNewTokenMutationDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): CreateNewTokenMutationDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): CreateNewTokenMutationDefaultResponse {
        return api.createMultipleTokensMutation(
            CreateMultipleTokensMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_createMultipleTokensMutation.submit
        )
    }

    override fun submitWatch(args: CreateMultipleTokensMutationRequest): CreateNewTokenMutationDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): CreateNewTokenMutationDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): CreateNewTokenMutationDefaultResponse {
        return api.createMultipleTokensMutation(
            CreateMultipleTokensMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_createMultipleTokensMutation.submit
        )
    }

}