package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class DestroyCollectionMutationServiceImpl(basePath: String) :
    MutationService<DestroyRequest, DestroyDefaultResponse>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: DestroyRequest): UnsignedTxPayloadResponse {
        val res = api.destroy(args, CollectionsApi.Use_destroy.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: DestroyRequest): FeeResponse {
        val res = api.destroy(args, CollectionsApi.Use_destroy.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.destroy(
            DestroyRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), CollectionsApi.Use_destroy.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.destroy(
            DestroyRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_destroy.build, true
        )
        return res.fee!!
    }

    override fun sign(args: DestroyRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw!!.data!!)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: DestroyRequest): DestroyDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): DestroyDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): DestroyDefaultResponse {
        return api.destroy(
            DestroyRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_destroy.submit
        )
    }

    override fun submitWatch(args: DestroyRequest): DestroyDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): DestroyDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): DestroyDefaultResponse {
        return api.destroy(
            DestroyRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_destroy.submit
        )
    }

}