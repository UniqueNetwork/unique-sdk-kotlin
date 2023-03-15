package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class AddToAllowListMutationServiceImpl(basePath: String) :
    MutationService<AddToAllowListRequest, AddToAllowListDefaultResponse>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: AddToAllowListRequest): UnsignedTxPayloadResponse {
        val res = api.addToAllowList(args, CollectionsApi.Use_addToAllowList.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: AddToAllowListRequest): FeeResponse {
        val res = api.addToAllowList(args, CollectionsApi.Use_addToAllowList.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.addToAllowList(
            AddToAllowListRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), CollectionsApi.Use_addToAllowList.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.addToAllowList(
            AddToAllowListRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_addToAllowList.build, true
        )
        return res.fee!!
    }

    override fun sign(args: AddToAllowListRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: AddToAllowListRequest): AddToAllowListDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): AddToAllowListDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): AddToAllowListDefaultResponse {
        return api.addToAllowList(
            AddToAllowListRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_addToAllowList.submit
        )
    }

    override fun submitWatch(args: AddToAllowListRequest): AddToAllowListDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): AddToAllowListDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): AddToAllowListDefaultResponse {
        return api.addToAllowList(
            AddToAllowListRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_addToAllowList.submit
        )
    }

}