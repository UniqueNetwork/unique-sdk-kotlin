package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class SetPropertyPermissionsMutationServiceImpl(basePath: String) :
    MutationService<SetPropertyPermissionsRequest>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: SetPropertyPermissionsRequest): UnsignedTxPayloadResponse {
        val res = api.setPropertyPermissions(args, CollectionsApi.Use_setPropertyPermissions.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: SetPropertyPermissionsRequest): FeeResponse {
        val res = api.setPropertyPermissions(args, CollectionsApi.Use_setPropertyPermissions.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.setPropertyPermissions(
            SetPropertyPermissionsRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), CollectionsApi.Use_setPropertyPermissions.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.setPropertyPermissions(
            SetPropertyPermissionsRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_setPropertyPermissions.build, true
        )
        return res.fee!!
    }

    override fun sign(args: SetPropertyPermissionsRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: SetPropertyPermissionsRequest): SubmitResultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): SubmitResultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.setPropertyPermissions(
            SetPropertyPermissionsRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_setPropertyPermissions.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: SetPropertyPermissionsRequest): SubmitResultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): SubmitResultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.setPropertyPermissions(
            SetPropertyPermissionsRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_setPropertyPermissions.submit
        )
        return SubmitResultResponse(response.hash)
    }

}