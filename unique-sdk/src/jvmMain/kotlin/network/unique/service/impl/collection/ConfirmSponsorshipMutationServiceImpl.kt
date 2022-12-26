package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class ConfirmSponsorshipMutationServiceImpl(basePath: String) :
    MutationService<ConfirmSponsorshipRequest>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: ConfirmSponsorshipRequest): UnsignedTxPayloadResponse {
        val res = api.confirmSponsorship(args, CollectionsApi.Use_confirmSponsorship.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: ConfirmSponsorshipRequest): FeeResponse {
        val res = api.confirmSponsorship(args, CollectionsApi.Use_confirmSponsorship.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.confirmSponsorship(
            ConfirmSponsorshipRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), CollectionsApi.Use_confirmSponsorship.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.confirmSponsorship(
            ConfirmSponsorshipRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_confirmSponsorship.build, true
        )
        return res.fee!!
    }

    override fun sign(args: ConfirmSponsorshipRequest, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: ConfirmSponsorshipRequest, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.confirmSponsorship(
            ConfirmSponsorshipRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_confirmSponsorship.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: ConfirmSponsorshipRequest, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.confirmSponsorship(
            ConfirmSponsorshipRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_confirmSponsorship.result
        )
        return SubmitResultResponse(response.hash)
    }

}