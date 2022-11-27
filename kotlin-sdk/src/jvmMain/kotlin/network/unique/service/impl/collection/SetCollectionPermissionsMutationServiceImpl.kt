package network.unique.service.impl.collection

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.service.MutationService

class SetCollectionPermissionsMutationServiceImpl(private val signer: Signer, basePath: String) :
    MutationService<SetCollectionPermissionsBody>() {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun build(args: SetCollectionPermissionsBody): UnsignedTxPayloadResponse {
        val res = api.setPermissions(args, CollectionsApi.Use_setPermissions.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: SetCollectionPermissionsBody): FeeResponse {
        val res = api.setPermissions(args, CollectionsApi.Use_setPermissions.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.setPermissions(
            SetCollectionPermissionsBody(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), CollectionsApi.Use_setPermissions.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.setPermissions(
            SetCollectionPermissionsBody(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), CollectionsApi.Use_setPermissions.build, true
        )
        return res.fee!!
    }

    override fun sign(args: SetCollectionPermissionsBody, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val signature = signer.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: SetCollectionPermissionsBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.setPermissions(
            SetCollectionPermissionsBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_setPermissions.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: SetCollectionPermissionsBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.setPermissions(
            SetCollectionPermissionsBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), CollectionsApi.Use_setPermissions.submitWatch
        )
        return SubmitResultResponse(response.hash)
    }

}