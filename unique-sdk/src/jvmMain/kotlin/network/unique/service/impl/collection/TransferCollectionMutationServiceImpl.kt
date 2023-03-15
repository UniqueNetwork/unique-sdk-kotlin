package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class TransferCollectionMutationServiceImpl(basePath: String) :
    MutationService<TransferRequest, TransferDefaultResponse>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: TransferRequest): UnsignedTxPayloadResponse {
        val res = api.transfer(args, CollectionsApi.Use_transfer.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: TransferRequest): FeeResponse {
        val res = api.transfer(args, CollectionsApi.Use_transfer.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.transfer(
            TransferRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), CollectionsApi.Use_transfer.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.transfer(
            TransferRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_transfer.build, true
        )
        return res.fee!!
    }

    override fun sign(args: TransferRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: TransferRequest): TransferDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): TransferDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): TransferDefaultResponse {
        return api.transfer(
            TransferRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_transfer.submit
        )
    }

    override fun submitWatch(args: TransferRequest): TransferDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): TransferDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): TransferDefaultResponse {
        return api.transfer(
            TransferRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_transfer.submit
        )
    }

}