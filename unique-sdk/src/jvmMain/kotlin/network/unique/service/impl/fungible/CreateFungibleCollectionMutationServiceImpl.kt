package network.unique.service.impl.fungible

import network.unique.api.FungibleApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class CreateFungibleCollectionMutationServiceImpl(basePath: String) :
    MutationService<CreateFungibleCollectionMutationRequest>() {

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

    override fun sign(args: CreateFungibleCollectionMutationRequest, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: CreateFungibleCollectionMutationRequest, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.createFungibleCollectionMutation(
            CreateFungibleCollectionMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), FungibleApi.Use_createFungibleCollectionMutation.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: CreateFungibleCollectionMutationRequest, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.createFungibleCollectionMutation(
            CreateFungibleCollectionMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), FungibleApi.Use_createFungibleCollectionMutation.result
        )
        return SubmitResultResponse(response.hash)
    }

}