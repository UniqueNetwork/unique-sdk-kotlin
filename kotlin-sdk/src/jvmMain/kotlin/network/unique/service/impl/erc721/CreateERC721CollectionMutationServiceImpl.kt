package network.unique.service.impl.erc721

import network.unique.api.ERC721Api
import network.unique.model.*
import network.unique.service.MutationService
import network.unique.signer.CryptoScheme
import network.unique.signer.Pair

class CreateERC721CollectionMutationServiceImpl(basePath: String) : MutationService<CreateERC721CollectionBody>() {

    private val api: ERC721Api = ERC721Api(basePath)

    override fun build(args: CreateERC721CollectionBody): UnsignedTxPayloadResponse {
        val res = api.eRC721ControllerCreateCollection(args, ERC721Api.Use_eRC721ControllerCreateCollection.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: CreateERC721CollectionBody): FeeResponse {
        val res = api.eRC721ControllerCreateCollection(args, ERC721Api.Use_eRC721ControllerCreateCollection.build, true)
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.eRC721ControllerCreateCollection(
            CreateERC721CollectionBody(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), ERC721Api.Use_eRC721ControllerCreateCollection.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.eRC721ControllerCreateCollection(
            CreateERC721CollectionBody(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), ERC721Api.Use_eRC721ControllerCreateCollection.build, true
        )
        return res.fee!!
    }

    override fun sign(args: CreateERC721CollectionBody, seed: String): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload, seed)
    }

    override fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody {
        val keyPair = Pair.fromSuri(CryptoScheme.Sr25519, seed, null)

        val signature = keyPair.sign(toByteArray(args.signerPayloadRaw.data.substring(2)))
            .joinToString("") { eachByte -> "%02x".format(eachByte) }

        return SubmitTxBody(args.signerPayloadJSON, "0x01$signature")
    }

    override fun submit(args: CreateERC721CollectionBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): SubmitResultResponse {
        val response = api.eRC721ControllerCreateCollection(
            CreateERC721CollectionBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), ERC721Api.Use_eRC721ControllerCreateCollection.submit
        )
        return SubmitResultResponse(response.hash)
    }

    override fun submitWatch(args: CreateERC721CollectionBody, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse {
        val signedBody = sign(args, seed)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): SubmitResultResponse {
        val response = api.eRC721ControllerCreateCollection(
            CreateERC721CollectionBody(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), ERC721Api.Use_eRC721ControllerCreateCollection.submitWatch
        )
        return SubmitResultResponse(response.hash)
    }

}