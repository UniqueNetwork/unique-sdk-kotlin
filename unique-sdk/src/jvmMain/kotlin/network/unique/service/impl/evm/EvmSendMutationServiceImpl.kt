package network.unique.service.impl.evm

import network.unique.api.EvmApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class EvmSendMutationServiceImpl(basePath: String) :
    MutationService<EvmSendArgumentsDto>() {

    private val api: EvmApi = EvmApi(basePath)

    override fun build(args: EvmSendArgumentsDto): UnsignedTxPayloadResponse {
        val res = api.evmSendMutation(args, EvmApi.Use_evmSendMutation.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: EvmSendArgumentsDto): FeeResponse {
        val res = api.evmSendMutation(args, EvmApi.Use_evmSendMutation.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.evmSendMutation(
            EvmSendArgumentsDto(
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
            EvmSendArgumentsDto(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), EvmApi.Use_evmSendMutation.build, true
        )
        return res.fee!!
    }

    override fun sign(args: EvmSendArgumentsDto, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: EvmSendArgumentsDto, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.evmSendMutation(
            EvmSendArgumentsDto(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), EvmApi.Use_evmSendMutation.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: EvmSendArgumentsDto, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.evmSendMutation(
            EvmSendArgumentsDto(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), EvmApi.Use_evmSendMutation.submitWatch
        )
        return SubmitResultResponse(response.hash)
    }

}