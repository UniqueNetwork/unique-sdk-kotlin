package network.unique.service.impl.token

import network.unique.api.TokensApi
import network.unique.model.*
import network.unique.service.MutationService
import network.unique.signer.CryptoScheme
import network.unique.signer.Pair

class UnnestTokenMutationServiceImpl(basePath: String) : MutationService<UnnestTokenBody>() {

    private val api: TokensApi = TokensApi(basePath)

    override fun build(args: UnnestTokenBody): UnsignedTxPayloadResponse {
        val res = api.unnestToken(args, TokensApi.Use_unnestToken.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: UnnestTokenBody): FeeResponse {
        val res = api.unnestToken(args, TokensApi.Use_unnestToken.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.unnestToken(
            UnnestTokenBody(
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
            UnnestTokenBody(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), TokensApi.Use_unnestToken.build, true
        )
        return res.fee!!
    }

    override fun sign(args: UnnestTokenBody, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val keyPair = Pair.fromSuri(CryptoScheme.Sr25519, seed, null)

        val signature = keyPair.sign(toByteArray(args.signerPayloadRaw.data.substring(2)))
            .joinToString("") { eachByte -> "%02x".format(eachByte) }

        return SubmitTxBody(args.signerPayloadJSON, "0x01$signature")
    }

    override fun submit(args: UnnestTokenBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.unnestToken(
            UnnestTokenBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_unnestToken.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: UnnestTokenBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.unnestToken(
            UnnestTokenBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_unnestToken.submitWatch
        )
        return SubmitResultResponse(response.hash)
    }

}