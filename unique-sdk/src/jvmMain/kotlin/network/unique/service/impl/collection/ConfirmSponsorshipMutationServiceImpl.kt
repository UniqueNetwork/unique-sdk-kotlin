package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class ConfirmSponsorshipMutationServiceImpl(basePath: String) :
    MutationService<ConfirmSponsorshipRequest, ConfirmSponsorshipDefaultResponse>() {

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

    override fun sign(args: ConfirmSponsorshipRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: ConfirmSponsorshipRequest): ConfirmSponsorshipDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): ConfirmSponsorshipDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): ConfirmSponsorshipDefaultResponse {
        return api.confirmSponsorship(
            ConfirmSponsorshipRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_confirmSponsorship.submit
        )
    }

    override fun submitWatch(args: ConfirmSponsorshipRequest): ConfirmSponsorshipDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): ConfirmSponsorshipDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): ConfirmSponsorshipDefaultResponse {
        return api.confirmSponsorship(
            ConfirmSponsorshipRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_confirmSponsorship.submit
        )
    }

}