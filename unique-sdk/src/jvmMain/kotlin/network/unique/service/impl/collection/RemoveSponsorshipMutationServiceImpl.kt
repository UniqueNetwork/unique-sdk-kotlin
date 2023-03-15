package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class RemoveSponsorshipMutationServiceImpl(basePath: String) :
    MutationService<RemoveSponsorshipRequest, RemoveSponsorshipDefaultResponse>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: RemoveSponsorshipRequest): UnsignedTxPayloadResponse {
        val res = api.removeSponsorship(args, CollectionsApi.Use_removeSponsorship.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: RemoveSponsorshipRequest): FeeResponse {
        val res = api.removeSponsorship(args, CollectionsApi.Use_removeSponsorship.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.removeSponsorship(
            RemoveSponsorshipRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), CollectionsApi.Use_removeSponsorship.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.removeSponsorship(
            RemoveSponsorshipRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_removeSponsorship.build, true
        )
        return res.fee!!
    }

    override fun sign(args: RemoveSponsorshipRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw!!.data!!)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: RemoveSponsorshipRequest): RemoveSponsorshipDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): RemoveSponsorshipDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): RemoveSponsorshipDefaultResponse {
        return api.removeSponsorship(
            RemoveSponsorshipRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_removeSponsorship.submit
        )
    }

    override fun submitWatch(args: RemoveSponsorshipRequest): RemoveSponsorshipDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): RemoveSponsorshipDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): RemoveSponsorshipDefaultResponse {
        return api.removeSponsorship(
            RemoveSponsorshipRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_removeSponsorship.submit
        )
    }

}