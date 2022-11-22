package network.unique.service.impl.token

import network.unique.api.TokensApi
import network.unique.model.*
import network.unique.service.MutationService
import network.unique.signer.CryptoScheme
import network.unique.signer.Pair

class SetTokenPropertiesMutationServiceImpl(basePath: String) : MutationService<SetTokenPropertiesBody>() {

    private val api: TokensApi = TokensApi(basePath)

    override fun build(args: SetTokenPropertiesBody): UnsignedTxPayloadResponse {
        val res = api.setTokenProperties(args, TokensApi.Use_setTokenProperties.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: SetTokenPropertiesBody): FeeResponse {
        val res = api.setTokenProperties(args, TokensApi.Use_setTokenProperties.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.setTokenProperties(
            SetTokenPropertiesBody(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), TokensApi.Use_setTokenProperties.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.setTokenProperties(
            SetTokenPropertiesBody(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), TokensApi.Use_setTokenProperties.build, true
        )
        return res.fee!!
    }

    override fun sign(args: SetTokenPropertiesBody, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val keyPair = Pair.fromSuri(CryptoScheme.Sr25519, seed, null)

        val signature = keyPair.sign(toByteArray(args.signerPayloadRaw.data.substring(2)))
            .joinToString("") { eachByte -> "%02x".format(eachByte) }

        return SubmitTxBody(args.signerPayloadJSON, "0x01$signature")
    }

    override fun submit(args: SetTokenPropertiesBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.setTokenProperties(
            SetTokenPropertiesBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_setTokenProperties.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: SetTokenPropertiesBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.setTokenProperties(
            SetTokenPropertiesBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_setTokenProperties.submitWatch
        )
        return SubmitResultResponse(response.hash)
    }

}