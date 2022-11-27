package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.service.MutationService

class SetTransfersEnabledMutationServiceImpl(private val signer: Signer, basePath: String) :
    MutationService<SetTransfersEnabledBody>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: SetTransfersEnabledBody): UnsignedTxPayloadResponse {
        val res = api.setTransfersEnabled(args, CollectionsApi.Use_setTransfersEnabled.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: SetTransfersEnabledBody): FeeResponse {
        val res = api.setTransfersEnabled(args, CollectionsApi.Use_setTransfersEnabled.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.setTransfersEnabled(
            SetTransfersEnabledBody(
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
            SetTransfersEnabledBody(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_setTransfersEnabled.build, true
        )
        return res.fee!!
    }

    override fun sign(args: SetTransfersEnabledBody, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val signature = signer.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: SetTransfersEnabledBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.setTransfersEnabled(
            SetTransfersEnabledBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_setTransfersEnabled.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: SetTransfersEnabledBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.setTransfersEnabled(
            SetTransfersEnabledBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_setTransfersEnabled.submitWatch
        )
        return SubmitResultResponse(response.hash)
    }

}