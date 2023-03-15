package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class CreateCollectionMutationServiceImpl(basePath: String) :
    MutationService<CreateCollectionMutationRequest, CreateCollectionMutationDefaultResponse>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: CreateCollectionMutationRequest): UnsignedTxPayloadResponse {
        val res = api.createCollectionMutation(args, CollectionsApi.Use_createCollectionMutation.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: CreateCollectionMutationRequest): FeeResponse {
        val res = api.createCollectionMutation(args, CollectionsApi.Use_createCollectionMutation.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.createCollectionMutation(
            CreateCollectionMutationRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), CollectionsApi.Use_createCollectionMutation.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.createCollectionMutation(
            CreateCollectionMutationRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_createCollectionMutation.build, true
        )
        return res.fee!!
    }

    override fun sign(args: CreateCollectionMutationRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: CreateCollectionMutationRequest): CreateCollectionMutationDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): CreateCollectionMutationDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): CreateCollectionMutationDefaultResponse {
        return api.createCollectionMutation(
            CreateCollectionMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_createCollectionMutation.submit
        )
    }

    override fun submitWatch(args: CreateCollectionMutationRequest): CreateCollectionMutationDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): CreateCollectionMutationDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): CreateCollectionMutationDefaultResponse {
        return api.createCollectionMutation(
            CreateCollectionMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_createCollectionMutation.submit
        )
    }

}