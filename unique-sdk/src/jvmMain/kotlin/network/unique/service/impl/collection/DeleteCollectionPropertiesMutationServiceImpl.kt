package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class DeleteCollectionPropertiesMutationServiceImpl(basePath: String) :
    MutationService<DeleteCollectionPropertiesBody>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: DeleteCollectionPropertiesBody): UnsignedTxPayloadResponse {
        val res = api.deleteCollectionProperties(args, CollectionsApi.Use_deleteCollectionProperties.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: DeleteCollectionPropertiesBody): FeeResponse {
        val res = api.deleteCollectionProperties(args, CollectionsApi.Use_deleteCollectionProperties.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.deleteCollectionProperties(
            DeleteCollectionPropertiesBody(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), CollectionsApi.Use_deleteCollectionProperties.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.deleteCollectionProperties(
            DeleteCollectionPropertiesBody(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_deleteCollectionProperties.build, true
        )
        return res.fee!!
    }

    override fun sign(args: DeleteCollectionPropertiesBody, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: DeleteCollectionPropertiesBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.deleteCollectionProperties(
            DeleteCollectionPropertiesBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_deleteCollectionProperties.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: DeleteCollectionPropertiesBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.deleteCollectionProperties(
            DeleteCollectionPropertiesBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_deleteCollectionProperties.submitWatch
        )
        return SubmitResultResponse(response.hash)
    }

}