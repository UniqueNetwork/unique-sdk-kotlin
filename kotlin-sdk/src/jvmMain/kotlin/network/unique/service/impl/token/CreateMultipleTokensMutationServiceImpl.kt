package network.unique.service.impl.token

import network.unique.api.TokensApi
import network.unique.model.*
import network.unique.service.MutationService
import network.unique.signer.CryptoScheme
import network.unique.signer.Pair

class CreateMultipleTokensMutationServiceImpl(basePath: String) : MutationService<CreateMultipleTokensBody>() {

    private val api: TokensApi = TokensApi(basePath)

    override fun build(args: CreateMultipleTokensBody): UnsignedTxPayloadResponse {
        val res = api.createMultipleTokensMutation(args, TokensApi.Use_createMultipleTokensMutation.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: CreateMultipleTokensBody): FeeResponse {
        val res = api.createMultipleTokensMutation(args, TokensApi.Use_createMultipleTokensMutation.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.createMultipleTokensMutation(
            CreateMultipleTokensBody(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), TokensApi.Use_createMultipleTokensMutation.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.createMultipleTokensMutation(
            CreateMultipleTokensBody(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), TokensApi.Use_createMultipleTokensMutation.build, true
        )
        return res.fee!!
    }

    override fun sign(args: CreateMultipleTokensBody, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val keyPair = Pair.fromSuri(CryptoScheme.Sr25519, seed, null)

        val signature = keyPair.sign(toByteArray(args.signerPayloadRaw.data.substring(2)))
            .joinToString("") { eachByte -> "%02x".format(eachByte) }

        return SubmitTxBody(args.signerPayloadJSON, "0x01$signature")
    }

    override fun submit(args: CreateMultipleTokensBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.createMultipleTokensMutation(
            CreateMultipleTokensBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_createMultipleTokensMutation.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: CreateMultipleTokensBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.createMultipleTokensMutation(
            CreateMultipleTokensBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_createMultipleTokensMutation.submitWatch
        )
        return SubmitResultResponse(response.hash)
    }

}