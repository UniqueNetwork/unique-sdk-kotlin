package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.service.MutationService

class AddToAllowListMutationServiceImpl(private val signerWrapper: SignerWrapper, basePath: String) :
    MutationService<AddToAllowListBody>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: AddToAllowListBody): UnsignedTxPayloadResponse {
        val res = api.addToAllowList(args, CollectionsApi.Use_addToAllowList.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: AddToAllowListBody): FeeResponse {
        val res = api.addToAllowList(args, CollectionsApi.Use_addToAllowList.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.addToAllowList(
            AddToAllowListBody(
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
            AddToAllowListBody(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_addToAllowList.build, true
        )
        return res.fee!!
    }

    override fun sign(args: AddToAllowListBody, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val signature = signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: AddToAllowListBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.addToAllowList(
            AddToAllowListBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_addToAllowList.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: AddToAllowListBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.addToAllowList(
            AddToAllowListBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_addToAllowList.submitWatch
        )
        return SubmitResultResponse(response.hash)
    }

}