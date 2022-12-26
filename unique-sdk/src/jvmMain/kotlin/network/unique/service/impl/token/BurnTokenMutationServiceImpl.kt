package network.unique.service.impl.token

import network.unique.api.TokensApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class BurnTokenMutationServiceImpl(basePath: String) : MutationService<BurnTokenRequest>() {

    private val api: TokensApi = TokensApi(basePath)

    override fun build(args: BurnTokenRequest): UnsignedTxPayloadResponse {
        val res = api.burnToken(args, TokensApi.Use_burnToken.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: BurnTokenRequest): FeeResponse {
        val res = api.burnToken(args, TokensApi.Use_burnToken.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.burnToken(
            BurnTokenRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), TokensApi.Use_burnToken.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.burnToken(
            BurnTokenRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), TokensApi.Use_burnToken.build, true
        )
        return res.fee!!
    }

    override fun sign(args: BurnTokenRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: BurnTokenRequest): SubmitResultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): SubmitResultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.burnToken(
            BurnTokenRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_burnToken.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: BurnTokenRequest): SubmitResultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): SubmitResultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.burnToken(
            BurnTokenRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_burnToken.submit
        )
        return SubmitResultResponse(response.hash)
    }

}