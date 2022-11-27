package network.unique.service.impl.fungible

import network.unique.api.FungibleApi
import network.unique.model.*
import network.unique.service.MutationService
import network.unique.signer.CryptoScheme
import network.unique.signer.Pair

class TransferTokensMutationServiceImpl(basePath: String) : MutationService<TransferTokensArgsDto>() {

    private val api: FungibleApi = FungibleApi(basePath)

    override fun build(args: TransferTokensArgsDto): UnsignedTxPayloadResponse {
        val res = api.transferTokensMutation(args, FungibleApi.Use_transferTokensMutation.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: TransferTokensArgsDto): FeeResponse {
        val res = api.transferTokensMutation(args, FungibleApi.Use_transferTokensMutation.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.transferTokensMutation(
            TransferTokensArgsDto(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), FungibleApi.Use_transferTokensMutation.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.transferTokensMutation(
            TransferTokensArgsDto(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), FungibleApi.Use_transferTokensMutation.build, true
        )
        return res.fee!!
    }

    override fun sign(args: TransferTokensArgsDto, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val keyPair = Pair.fromSuri(CryptoScheme.Sr25519, seed, null)

        val signature = keyPair.sign(toByteArray(args.signerPayloadRaw.data.substring(2)))
            .joinToString("") { eachByte -> "%02x".format(eachByte) }

        return SubmitTxBody(args.signerPayloadJSON, "0x01$signature")
    }

    override fun submit(args: TransferTokensArgsDto, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.transferTokensMutation(
            TransferTokensArgsDto(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), FungibleApi.Use_transferTokensMutation.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: TransferTokensArgsDto, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.transferTokensMutation(
            TransferTokensArgsDto(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), FungibleApi.Use_transferTokensMutation.submitWatch
        )
        return SubmitResultResponse(response.hash)
    }

}