package network.unique.service.impl.token

import network.unique.api.TokensApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class DeleteTokenPropertiesMutationServiceImpl(basePath: String) :
    MutationService<DeleteTokenPropertiesRequest>() {

    private val api: TokensApi = TokensApi(basePath)

    override fun build(args: DeleteTokenPropertiesRequest): UnsignedTxPayloadResponse {
        val res = api.deleteTokenProperties(args, TokensApi.Use_deleteTokenProperties.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: DeleteTokenPropertiesRequest): FeeResponse {
        val res = api.deleteTokenProperties(args, TokensApi.Use_deleteTokenProperties.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.deleteTokenProperties(
            DeleteTokenPropertiesRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), TokensApi.Use_deleteTokenProperties.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.deleteTokenProperties(
            DeleteTokenPropertiesRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), TokensApi.Use_deleteTokenProperties.build, true
        )
        return res.fee!!
    }

    override fun sign(args: DeleteTokenPropertiesRequest, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: DeleteTokenPropertiesRequest, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.deleteTokenProperties(
            DeleteTokenPropertiesRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_deleteTokenProperties.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: DeleteTokenPropertiesRequest, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.deleteTokenProperties(
            DeleteTokenPropertiesRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_deleteTokenProperties.result
        )
        return SubmitResultResponse(response.hash)
    }

}