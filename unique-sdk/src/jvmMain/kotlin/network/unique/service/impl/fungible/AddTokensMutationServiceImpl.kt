package network.unique.service.impl.fungible

import network.unique.api.FungibleApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class AddTokensMutationServiceImpl(basePath: String) :
    MutationService<AddTokensMutationRequest, AddTokensMutationDefaultResponse>() {

    private val api: FungibleApi = FungibleApi(basePath)

    override fun build(args: AddTokensMutationRequest): UnsignedTxPayloadResponse {
        val res = api.addTokensMutation(args, FungibleApi.Use_addTokensMutation.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: AddTokensMutationRequest): FeeResponse {
        val res = api.addTokensMutation(args, FungibleApi.Use_addTokensMutation.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.addTokensMutation(
            AddTokensMutationRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), FungibleApi.Use_addTokensMutation.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.addTokensMutation(
            AddTokensMutationRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), FungibleApi.Use_addTokensMutation.build, true
        )
        return res.fee!!
    }

    override fun sign(args: AddTokensMutationRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw!!.data!!)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: AddTokensMutationRequest): AddTokensMutationDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): AddTokensMutationDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): AddTokensMutationDefaultResponse {
        return api.addTokensMutation(
            AddTokensMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), FungibleApi.Use_addTokensMutation.submit
        )
    }

    override fun submitWatch(args: AddTokensMutationRequest): AddTokensMutationDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): AddTokensMutationDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): AddTokensMutationDefaultResponse {
        return api.addTokensMutation(
            AddTokensMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), FungibleApi.Use_addTokensMutation.submit
        )
    }

}