package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class RemoveFromAllowListMutationServiceImpl(basePath: String) :
    MutationService<RemoveFromAllowListRequest, RemoveFromAllowListDefaultResponse>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: RemoveFromAllowListRequest): UnsignedTxPayloadResponse {
        val res = api.removeFromAllowList(args, CollectionsApi.Use_removeFromAllowList.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: RemoveFromAllowListRequest): FeeResponse {
        val res = api.removeFromAllowList(args, CollectionsApi.Use_removeFromAllowList.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.removeFromAllowList(
            RemoveFromAllowListRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), CollectionsApi.Use_removeFromAllowList.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.removeFromAllowList(
            RemoveFromAllowListRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_removeFromAllowList.build, true
        )
        return res.fee!!
    }

    override fun sign(args: RemoveFromAllowListRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: RemoveFromAllowListRequest): RemoveFromAllowListDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): RemoveFromAllowListDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): RemoveFromAllowListDefaultResponse {
        return api.removeFromAllowList(
            RemoveFromAllowListRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_removeFromAllowList.submit
        )
    }

    override fun submitWatch(args: RemoveFromAllowListRequest): RemoveFromAllowListDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): RemoveFromAllowListDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): RemoveFromAllowListDefaultResponse {
        return api.removeFromAllowList(
            RemoveFromAllowListRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_removeFromAllowList.submit
        )
    }

}