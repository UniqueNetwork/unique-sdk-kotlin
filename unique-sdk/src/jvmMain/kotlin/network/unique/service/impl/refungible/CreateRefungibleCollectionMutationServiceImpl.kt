package network.unique.service.impl.refungible

import network.unique.api.RefungibleApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class CreateRefungibleCollectionMutationServiceImpl(basePath: String) :
    MutationService<CreateRefungibleCollectionMutationRequest>() {

    private val api: RefungibleApi = RefungibleApi(basePath)

    override fun build(args: CreateRefungibleCollectionMutationRequest): UnsignedTxPayloadResponse {
        val res =
            api.createRefungibleCollectionMutation(args, RefungibleApi.Use_createRefungibleCollectionMutation.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: CreateRefungibleCollectionMutationRequest): FeeResponse {
        val res = api.createRefungibleCollectionMutation(
            args,
            RefungibleApi.Use_createRefungibleCollectionMutation.build,
            true
        )
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.createRefungibleCollectionMutation(
            CreateRefungibleCollectionMutationRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), RefungibleApi.Use_createRefungibleCollectionMutation.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.createRefungibleCollectionMutation(
            CreateRefungibleCollectionMutationRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), RefungibleApi.Use_createRefungibleCollectionMutation.build, true
        )
        return res.fee!!
    }

    override fun sign(args: CreateRefungibleCollectionMutationRequest, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: CreateRefungibleCollectionMutationRequest, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.createRefungibleCollectionMutation(
            CreateRefungibleCollectionMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), RefungibleApi.Use_createRefungibleCollectionMutation.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: CreateRefungibleCollectionMutationRequest, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.createRefungibleCollectionMutation(
            CreateRefungibleCollectionMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), RefungibleApi.Use_createRefungibleCollectionMutation.submit
        )
        return SubmitResultResponse(response.hash)
    }

}