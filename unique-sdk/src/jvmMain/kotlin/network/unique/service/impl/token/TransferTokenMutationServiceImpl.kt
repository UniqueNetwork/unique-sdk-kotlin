package network.unique.service.impl.token

import network.unique.api.TokensApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class TransferTokenMutationServiceImpl(basePath: String) :
    MutationService<TransferTokenRequest, TransferTokenDefaultResponse>() {

    private val api: TokensApi = TokensApi(basePath)

    override fun build(args: TransferTokenRequest): UnsignedTxPayloadResponse {
        val res = api.transferToken(args, TokensApi.Use_transferToken.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: TransferTokenRequest): FeeResponse {
        val res = api.transferToken(args, TokensApi.Use_transferToken.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.transferToken(
            TransferTokenRequest(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), TokensApi.Use_transferToken.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.transferToken(
            TransferTokenRequest(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), TokensApi.Use_transferToken.build, true
        )
        return res.fee!!
    }

    override fun sign(args: TransferTokenRequest): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: TransferTokenRequest): TransferTokenDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): TransferTokenDefaultResponse {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): TransferTokenDefaultResponse {
        return api.transferToken(
            TransferTokenRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_transferToken.submit
        )
    }

    override fun submitWatch(args: TransferTokenRequest): TransferTokenDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): TransferTokenDefaultResponse {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): TransferTokenDefaultResponse {
        return api.transferToken(
            TransferTokenRequest(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), TokensApi.Use_transferToken.submit
        )
    }

}