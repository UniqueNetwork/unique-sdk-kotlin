package network.unique.service.impl.erc721

import network.unique.api.ERC721Api
import network.unique.model.*
import network.unique.service.MutationService

class CreateERC721TokenMutationServiceImpl(private val signerWrapper: SignerWrapper, basePath: String) :
    MutationService<CreateERC721TokenBody>() {

    private val api: ERC721Api = ERC721Api(basePath)

    override fun build(args: CreateERC721TokenBody): UnsignedTxPayloadResponse {
        val res = api.eRC721ControllerCreateToken(args, ERC721Api.Use_eRC721ControllerCreateToken.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: CreateERC721TokenBody): FeeResponse {
        val res = api.eRC721ControllerCreateToken(args, ERC721Api.Use_eRC721ControllerCreateToken.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.eRC721ControllerCreateToken(
            CreateERC721TokenBody(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), ERC721Api.Use_eRC721ControllerCreateToken.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.eRC721ControllerCreateToken(
            CreateERC721TokenBody(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), ERC721Api.Use_eRC721ControllerCreateToken.build, true
        )
        return res.fee!!
    }

    override fun sign(args: CreateERC721TokenBody, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val signature = signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: CreateERC721TokenBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.eRC721ControllerCreateToken(
            CreateERC721TokenBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), ERC721Api.Use_eRC721ControllerCreateToken.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: CreateERC721TokenBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.eRC721ControllerCreateToken(
            CreateERC721TokenBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), ERC721Api.Use_eRC721ControllerCreateToken.submitWatch
        )
        return SubmitResultResponse(response.hash)
    }

}