package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class SetCollectionLimitsMutationServiceImpl(basePath: String) :
    MutationService<SetCollectionLimitsBody>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: SetCollectionLimitsBody): UnsignedTxPayloadResponse {
        val res = api.setCollectionLimits(args, CollectionsApi.Use_setCollectionLimits.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: SetCollectionLimitsBody): FeeResponse {
        val res = api.setCollectionLimits(args, CollectionsApi.Use_setCollectionLimits.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.setCollectionLimits(
            SetCollectionLimitsBody(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), CollectionsApi.Use_setCollectionLimits.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.setCollectionLimits(
            SetCollectionLimitsBody(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_setCollectionLimits.build, true
        )
        return res.fee!!
    }

    override fun sign(args: SetCollectionLimitsBody, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: SetCollectionLimitsBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.setCollectionLimits(
            SetCollectionLimitsBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_setCollectionLimits.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: SetCollectionLimitsBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.setCollectionLimits(
            SetCollectionLimitsBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_setCollectionLimits.submitWatch
        )
        return SubmitResultResponse(response.hash)
    }

}