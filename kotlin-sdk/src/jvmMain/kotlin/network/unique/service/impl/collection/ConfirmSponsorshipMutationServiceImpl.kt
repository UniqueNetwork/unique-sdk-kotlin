package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.service.MutationService
import network.unique.signer.CryptoScheme
import network.unique.signer.Pair

class ConfirmSponsorshipMutationServiceImpl(basePath: String) : MutationService<ConfirmSponsorshipBody>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: ConfirmSponsorshipBody): UnsignedTxPayloadResponse {
        val res = api.confirmSponsorship(args, CollectionsApi.Use_confirmSponsorship.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: ConfirmSponsorshipBody): FeeResponse {
        val res = api.confirmSponsorship(args, CollectionsApi.Use_confirmSponsorship.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.confirmSponsorship(
            ConfirmSponsorshipBody(
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
            ConfirmSponsorshipBody(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_confirmSponsorship.build, true
        )
        return res.fee!!
    }

    override fun sign(args: ConfirmSponsorshipBody, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val keyPair = Pair.fromSuri(CryptoScheme.Sr25519, seed, null)

        val signature = keyPair.sign(toByteArray(args.signerPayloadRaw.data.substring(2)))
            .joinToString("") { eachByte -> "%02x".format(eachByte) }

        return SubmitTxBody(args.signerPayloadJSON, "0x01$signature")
    }

    override fun submit(args: ConfirmSponsorshipBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.confirmSponsorship(
            ConfirmSponsorshipBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_confirmSponsorship.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: ConfirmSponsorshipBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.confirmSponsorship(
            ConfirmSponsorshipBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_confirmSponsorship.submitWatch
        )
        return SubmitResultResponse(response.hash)
    }

}