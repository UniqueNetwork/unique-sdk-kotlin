package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class DeleteCollectionPropertiesMutationServiceImpl(basePath: String) :
    MutationService<DeleteCollectionPropertiesRequest, DeleteCollectionPropertiesDefaultResponse>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: DeleteCollectionPropertiesRequest): UnsignedTxPayloadResponse {
        val res = api.deleteCollectionProperties(args, CollectionsApi.Use_deleteCollectionProperties.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: DeleteCollectionPropertiesRequest): FeeResponse {
        val res = api.deleteCollectionProperties(args, CollectionsApi.Use_deleteCollectionProperties.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.deleteCollectionProperties(
            DeleteCollectionPropertiesRequest(
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
            DeleteCollectionPropertiesRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_deleteCollectionProperties.build, true
        )
        return res.fee!!
    }

    override fun sign(args: DeleteCollectionPropertiesRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw!!.data!!)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: DeleteCollectionPropertiesRequest): DeleteCollectionPropertiesDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): DeleteCollectionPropertiesDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): DeleteCollectionPropertiesDefaultResponse {
        return api.deleteCollectionProperties(
            DeleteCollectionPropertiesRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_deleteCollectionProperties.submit
        )
    }

    override fun submitWatch(args: DeleteCollectionPropertiesRequest): DeleteCollectionPropertiesDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): DeleteCollectionPropertiesDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): DeleteCollectionPropertiesDefaultResponse {
        return api.deleteCollectionProperties(
            DeleteCollectionPropertiesRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_deleteCollectionProperties.submit
        )
    }

}