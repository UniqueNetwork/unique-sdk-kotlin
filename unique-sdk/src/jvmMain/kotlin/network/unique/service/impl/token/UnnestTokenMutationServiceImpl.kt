package network.unique.service.impl.token

import network.unique.api.TokensApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class UnnestTokenMutationServiceImpl(basePath: String) :
    MutationService<UnnestTokenRequest, UnnestTokenDefaultResponse>() {

    private val api: TokensApi = TokensApi(basePath)

    override fun build(args: UnnestTokenRequest): UnsignedTxPayloadResponse {
        val res = api.unnestToken(args, TokensApi.Use_unnestToken.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: UnnestTokenRequest): FeeResponse {
        val res = api.unnestToken(args, TokensApi.Use_unnestToken.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.unnestToken(
            UnnestTokenRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), TokensApi.Use_unnestToken.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.unnestToken(
            UnnestTokenRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), TokensApi.Use_unnestToken.build, true
        )
        return res.fee!!
    }

    override fun sign(args: UnnestTokenRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw!!.data!!)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: UnnestTokenRequest): UnnestTokenDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): UnnestTokenDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): UnnestTokenDefaultResponse {
        return api.unnestToken(
            UnnestTokenRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_unnestToken.submit
        )
    }

    override fun submitWatch(args: UnnestTokenRequest): UnnestTokenDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): UnnestTokenDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): UnnestTokenDefaultResponse {
        return api.unnestToken(
            UnnestTokenRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_unnestToken.submit
        )
    }

}