package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.service.MutationService

class RemoveCollectionAdminMutationServiceImpl(private val signerWrapper: SignerWrapper, basePath: String) :
    MutationService<RemoveCollectionAdminBody>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: RemoveCollectionAdminBody): UnsignedTxPayloadResponse {
        val res = api.removeAdmin(args, CollectionsApi.Use_removeAdmin.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: RemoveCollectionAdminBody): FeeResponse {
        val res = api.removeAdmin(args, CollectionsApi.Use_removeAdmin.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.removeAdmin(
            RemoveCollectionAdminBody(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), CollectionsApi.Use_removeAdmin.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.removeAdmin(
            RemoveCollectionAdminBody(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_removeAdmin.build, true
        )
        return res.fee!!
    }

    override fun sign(args: RemoveCollectionAdminBody, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val signature = signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: RemoveCollectionAdminBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.removeAdmin(
            RemoveCollectionAdminBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_removeAdmin.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: RemoveCollectionAdminBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.removeAdmin(
            RemoveCollectionAdminBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_removeAdmin.submitWatch
        )
        return SubmitResultResponse(response.hash)
    }

}