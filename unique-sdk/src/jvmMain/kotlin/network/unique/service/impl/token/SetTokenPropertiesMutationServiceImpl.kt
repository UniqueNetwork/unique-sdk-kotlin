package network.unique.service.impl.token

import network.unique.api.TokensApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class SetTokenPropertiesMutationServiceImpl(basePath: String) :
    MutationService<SetTokenPropertiesRequest>() {

    private val api: TokensApi = TokensApi(basePath)

    override fun build(args: SetTokenPropertiesRequest): UnsignedTxPayloadResponse {
        val res = api.setTokenProperties(args, TokensApi.Use_setTokenProperties.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: SetTokenPropertiesRequest): FeeResponse {
        val res = api.setTokenProperties(args, TokensApi.Use_setTokenProperties.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.setTokenProperties(
            SetTokenPropertiesRequest(
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
            SetTokenPropertiesRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), TokensApi.Use_setTokenProperties.build, true
        )
        return res.fee!!
    }

    override fun sign(args: SetTokenPropertiesRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: SetTokenPropertiesRequest): SubmitResultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): SubmitResultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.setTokenProperties(
            SetTokenPropertiesRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_setTokenProperties.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: SetTokenPropertiesRequest): SubmitResultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): SubmitResultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.setTokenProperties(
            SetTokenPropertiesRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_setTokenProperties.submit
        )
        return SubmitResultResponse(response.hash)
    }

}