package network.unique.service.impl.refungible

import network.unique.api.RefungibleApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class CreateRefungibleCollectionMutationServiceImpl(basePath: String) :
    MutationService<CreateRefungibleCollectionMutationRequest, CreateCollectionMutationDefaultResponse>() {

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

    override fun sign(args: CreateRefungibleCollectionMutationRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw!!.data!!)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: CreateRefungibleCollectionMutationRequest): CreateCollectionMutationDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): CreateCollectionMutationDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): CreateCollectionMutationDefaultResponse {
        return api.createRefungibleCollectionMutation(
            CreateRefungibleCollectionMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), RefungibleApi.Use_createRefungibleCollectionMutation.submit
        )
    }

    override fun submitWatch(args: CreateRefungibleCollectionMutationRequest): CreateCollectionMutationDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): CreateCollectionMutationDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): CreateCollectionMutationDefaultResponse {
        return api.createRefungibleCollectionMutation(
            CreateRefungibleCollectionMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), RefungibleApi.Use_createRefungibleCollectionMutation.submit
        )
    }

}