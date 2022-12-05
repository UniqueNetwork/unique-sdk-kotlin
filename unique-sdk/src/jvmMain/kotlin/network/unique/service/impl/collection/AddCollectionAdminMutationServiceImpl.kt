package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.service.MutationService

class AddCollectionAdminMutationServiceImpl(private val signerWrapper: SignerWrapper, basePath: String) :
    MutationService<AddCollectionAdminBody>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: AddCollectionAdminBody): UnsignedTxPayloadResponse {
        val res = api.addAdmin(args, CollectionsApi.Use_addAdmin.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: AddCollectionAdminBody): FeeResponse {
        val res = api.addAdmin(args, CollectionsApi.Use_addAdmin.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.addAdmin(
            AddCollectionAdminBody(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), CollectionsApi.Use_addAdmin.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.addAdmin(
            AddCollectionAdminBody(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_addAdmin.build, true
        )
        return res.fee!!
    }

    override fun sign(args: AddCollectionAdminBody, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val signature = signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: AddCollectionAdminBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.addAdmin(
            AddCollectionAdminBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_addAdmin.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: AddCollectionAdminBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.addAdmin(
            AddCollectionAdminBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_addAdmin.submitWatch
        )
        return SubmitResultResponse(response.hash)
    }

}