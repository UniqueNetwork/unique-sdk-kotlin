package network.unique.service.impl.token

import network.unique.api.TokensApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class CreateTokenMutationServiceImpl(basePath: String) :
    MutationService<CreateNewTokenMutationRequest, CreateNewTokenMutationDefaultResponse>() {

    private val api: TokensApi = TokensApi(basePath)

    override fun build(args: CreateNewTokenMutationRequest): UnsignedTxPayloadResponse {
        val res = api.createNewTokenMutation(args, TokensApi.Use_createNewTokenMutation.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: CreateNewTokenMutationRequest): FeeResponse {
        val res = api.createNewTokenMutation(args, TokensApi.Use_createNewTokenMutation.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.createNewTokenMutation(
            CreateNewTokenMutationRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), TokensApi.Use_createNewTokenMutation.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.createNewTokenMutation(
            CreateNewTokenMutationRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), TokensApi.Use_createNewTokenMutation.build, true
        )
        return res.fee!!
    }

    override fun sign(args: CreateNewTokenMutationRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: CreateNewTokenMutationRequest): CreateNewTokenMutationDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): CreateNewTokenMutationDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): CreateNewTokenMutationDefaultResponse {
        return api.createNewTokenMutation(
            CreateNewTokenMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_createNewTokenMutation.submit
        )
    }

    override fun submitWatch(args: CreateNewTokenMutationRequest): CreateNewTokenMutationDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): CreateNewTokenMutationDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): CreateNewTokenMutationDefaultResponse {
        return api.createNewTokenMutation(
            CreateNewTokenMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_createNewTokenMutation.submit
        )
    }

}