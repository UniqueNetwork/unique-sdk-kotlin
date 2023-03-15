package network.unique.service.impl.fungible

import network.unique.api.FungibleApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class CreateFungibleCollectionMutationServiceImpl(basePath: String) :
    MutationService<CreateFungibleCollectionMutationRequest, CreateCollectionMutationDefaultResponse>() {

    private val api: FungibleApi = FungibleApi(basePath)

    override fun build(args: CreateFungibleCollectionMutationRequest): UnsignedTxPayloadResponse {
        val res = api.createFungibleCollectionMutation(args, FungibleApi.Use_createFungibleCollectionMutation.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: CreateFungibleCollectionMutationRequest): FeeResponse {
        val res = api.createFungibleCollectionMutation(args, FungibleApi.Use_createFungibleCollectionMutation.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.createFungibleCollectionMutation(
            CreateFungibleCollectionMutationRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), FungibleApi.Use_createFungibleCollectionMutation.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.createFungibleCollectionMutation(
            CreateFungibleCollectionMutationRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), FungibleApi.Use_createFungibleCollectionMutation.build, true
        )
        return res.fee!!
    }

    override fun sign(args: CreateFungibleCollectionMutationRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: CreateFungibleCollectionMutationRequest): CreateCollectionMutationDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): CreateCollectionMutationDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): CreateCollectionMutationDefaultResponse {
        return api.createFungibleCollectionMutation(
            CreateFungibleCollectionMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), FungibleApi.Use_createFungibleCollectionMutation.submit
        )
    }

    override fun submitWatch(args: CreateFungibleCollectionMutationRequest): CreateCollectionMutationDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): CreateCollectionMutationDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): CreateCollectionMutationDefaultResponse {
        return api.createFungibleCollectionMutation(
            CreateFungibleCollectionMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), FungibleApi.Use_createFungibleCollectionMutation.submit
        )
    }

}