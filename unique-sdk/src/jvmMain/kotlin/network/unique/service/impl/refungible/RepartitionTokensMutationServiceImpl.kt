package network.unique.service.impl.refungible

import network.unique.api.RefungibleApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class RepartitionTokensMutationServiceImpl(basePath: String) : MutationService<RepartitionTokenMutationRequest>() {

    private val api: RefungibleApi = RefungibleApi(basePath)

    override fun build(args: RepartitionTokenMutationRequest): UnsignedTxPayloadResponse {
        val res = api.repartitionTokenMutation(args, RefungibleApi.Use_repartitionTokenMutation.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: RepartitionTokenMutationRequest): FeeResponse {
        val res = api.repartitionTokenMutation(args, RefungibleApi.Use_repartitionTokenMutation.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.repartitionTokenMutation(
            RepartitionTokenMutationRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), RefungibleApi.Use_repartitionTokenMutation.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.repartitionTokenMutation(
            RepartitionTokenMutationRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), RefungibleApi.Use_repartitionTokenMutation.build, true
        )
        return res.fee!!
    }

    override fun sign(args: RepartitionTokenMutationRequest, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: RepartitionTokenMutationRequest, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.repartitionTokenMutation(
            RepartitionTokenMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), RefungibleApi.Use_repartitionTokenMutation.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: RepartitionTokenMutationRequest, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.repartitionTokenMutation(
            RepartitionTokenMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), RefungibleApi.Use_repartitionTokenMutation.result
        )
        return SubmitResultResponse(response.hash)
    }

}