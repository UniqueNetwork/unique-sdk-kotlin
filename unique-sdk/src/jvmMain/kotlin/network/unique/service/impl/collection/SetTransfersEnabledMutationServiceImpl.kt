package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class SetTransfersEnabledMutationServiceImpl(basePath: String) :
    MutationService<SetTransfersEnabledRequest, SetTransfersEnabledDefaultResponse>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: SetTransfersEnabledRequest): UnsignedTxPayloadResponse {
        val res = api.setTransfersEnabled(args, CollectionsApi.Use_setTransfersEnabled.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: SetTransfersEnabledRequest): FeeResponse {
        val res = api.setTransfersEnabled(args, CollectionsApi.Use_setTransfersEnabled.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.setTransfersEnabled(
            SetTransfersEnabledRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), CollectionsApi.Use_setTransfersEnabled.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.setTransfersEnabled(
            SetTransfersEnabledRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_setTransfersEnabled.build, true
        )
        return res.fee!!
    }

    override fun sign(args: SetTransfersEnabledRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: SetTransfersEnabledRequest): SetTransfersEnabledDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): SetTransfersEnabledDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SetTransfersEnabledDefaultResponse {
        return api.setTransfersEnabled(
            SetTransfersEnabledRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_setTransfersEnabled.submit
        )
    }

    override fun submitWatch(args: SetTransfersEnabledRequest): SetTransfersEnabledDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): SetTransfersEnabledDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SetTransfersEnabledDefaultResponse {
        return api.setTransfersEnabled(
            SetTransfersEnabledRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_setTransfersEnabled.submit
        )
    }

}