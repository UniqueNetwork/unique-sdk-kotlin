package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.service.MutationService

class RemoveSponsorshipMutationServiceImpl(private val signerWrapper: SignerWrapper, basePath: String) :
    MutationService<RemoveSponsorshipBody>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: RemoveSponsorshipBody): UnsignedTxPayloadResponse {
        val res = api.removeSponsorship(args, CollectionsApi.Use_removeSponsorship.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: RemoveSponsorshipBody): FeeResponse {
        val res = api.removeSponsorship(args, CollectionsApi.Use_removeSponsorship.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.removeSponsorship(
            RemoveSponsorshipBody(
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
            RemoveSponsorshipBody(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_removeSponsorship.build, true
        )
        return res.fee!!
    }

    override fun sign(args: RemoveSponsorshipBody, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val signature = signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: RemoveSponsorshipBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.removeSponsorship(
            RemoveSponsorshipBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_removeSponsorship.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: RemoveSponsorshipBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.removeSponsorship(
            RemoveSponsorshipBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_removeSponsorship.submitWatch
        )
        return SubmitResultResponse(response.hash)
    }

}