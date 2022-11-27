package network.unique.service.impl.fungible

import network.unique.api.FungibleApi
import network.unique.model.*
import network.unique.service.MutationService
import network.unique.signer.CryptoScheme
import network.unique.signer.Pair

class AddTokensMutationServiceImpl(basePath: String) : MutationService<AddTokensArgsDto>() {

    private val api: FungibleApi = FungibleApi(basePath)

    override fun build(args: AddTokensArgsDto): UnsignedTxPayloadResponse {
        val res = api.addTokensMutation(args, FungibleApi.Use_addTokensMutation.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: AddTokensArgsDto): FeeResponse {
        val res = api.addTokensMutation(args, FungibleApi.Use_addTokensMutation.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.addTokensMutation(
            AddTokensArgsDto(
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
            AddTokensArgsDto(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), FungibleApi.Use_addTokensMutation.build, true
        )
        return res.fee!!
    }

    override fun sign(args: AddTokensArgsDto, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val keyPair = Pair.fromSuri(CryptoScheme.Sr25519, seed, null)

        val signature = keyPair.sign(toByteArray(args.signerPayloadRaw.data.substring(2)))
            .joinToString("") { eachByte -> "%02x".format(eachByte) }

        return SubmitTxBody(args.signerPayloadJSON, "0x01$signature")
    }

    override fun submit(args: AddTokensArgsDto, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.addTokensMutation(
            AddTokensArgsDto(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), FungibleApi.Use_addTokensMutation.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: AddTokensArgsDto, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.addTokensMutation(
            AddTokensArgsDto(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), FungibleApi.Use_addTokensMutation.submitWatch
        )
        return SubmitResultResponse(response.hash)
    }

}