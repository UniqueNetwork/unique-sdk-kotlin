package network.unique.service.impl.evm

import network.unique.api.EvmApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class EvmSendMutationServiceImpl(basePath: String) :
    MutationService<EvmSendMutationRequest>() {

    private val api: EvmApi = EvmApi(basePath)

    override fun build(args: EvmSendMutationRequest): UnsignedTxPayloadResponse {
        val res = api.evmSendMutation(args, EvmApi.Use_evmSendMutation.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: EvmSendMutationRequest): FeeResponse {
        val res = api.evmSendMutation(args, EvmApi.Use_evmSendMutation.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.evmSendMutation(
            EvmSendMutationRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), EvmApi.Use_evmSendMutation.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.evmSendMutation(
            EvmSendMutationRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), EvmApi.Use_evmSendMutation.build, true
        )
        return res.fee!!
    }

    override fun sign(args: EvmSendMutationRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: EvmSendMutationRequest): SubmitResultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): SubmitResultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.evmSendMutation(
            EvmSendMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), EvmApi.Use_evmSendMutation.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: EvmSendMutationRequest): SubmitResultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): SubmitResultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.evmSendMutation(
            EvmSendMutationRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), EvmApi.Use_evmSendMutation.submit
        )
        return SubmitResultResponse(response.hash)
    }

}