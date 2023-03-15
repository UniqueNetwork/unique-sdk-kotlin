package network.unique.service.impl.refungible

import network.unique.api.RefungibleApi
import network.unique.model.*
import network.unique.sdk.UniqueSdk
import network.unique.service.MutationService

class BurnRefungibleMutationServiceImpl(basePath: String) : MutationService<BurnRequest1, BurnDefaultResponse1>() {

    private val api: RefungibleApi = RefungibleApi(basePath)

    override fun build(args: BurnRequest1): UnsignedTxPayloadResponse {
        val res =
            api.burn(args, RefungibleApi.Use_burn.build)
        return UnsignedTxPayloadResponse(res.signerPayloadJSON, res.signerPayloadRaw, res.signerPayloadHex, res.fee)
    }

    override fun getFee(args: BurnRequest1): FeeResponse {
        val res = api.burn(
            args,
            RefungibleApi.Use_burn.build,
            true
        )
        return res.fee!!
    }

    override fun getFee(args: UnsignedTxPayloadResponse): FeeResponse {
        val res = api.burn(
            BurnRequest1(
                signerPayloadHex = args.signerPayloadHex,
                signerPayloadRaw = args.signerPayloadRaw,
                signerPayloadJSON = args.signerPayloadJSON,
                fee = args.fee
            ), RefungibleApi.Use_burn.build, true
        )
        return res.fee!!
    }

    override fun getFee(args: SubmitTxBody): FeeResponse {
        val res = api.burn(
            BurnRequest1(
                signature = args.signature,
                signerPayloadJSON = args.signerPayloadJSON,
            ), RefungibleApi.Use_burn.build, true
        )
        return res.fee!!
    }

    override fun sign(args: BurnRequest1): SubmitTxBody {
        val signPayload = build(args)
        return sign(signPayload)
    }

    override fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody {
        val signature = UniqueSdk.signerWrapper.sign(args.signerPayloadRaw.data)

        return SubmitTxBody(args.signerPayloadJSON, signature)
    }

    override fun submit(args: BurnRequest1): BurnDefaultResponse1 {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: UnsignedTxPayloadResponse): BurnDefaultResponse1 {
        val signedBody = sign(args)
        return submit(signedBody)
    }

    override fun submit(args: SubmitTxBody): BurnDefaultResponse1 {
        return api.burn(
            BurnRequest1(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), RefungibleApi.Use_burn.submit
        )
    }

    override fun submitWatch(args: BurnRequest1): BurnDefaultResponse1 {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: UnsignedTxPayloadResponse): BurnDefaultResponse1 {
        val signedBody = sign(args)
        return submitWatch(signedBody)
    }

    override fun submitWatch(args: SubmitTxBody): BurnDefaultResponse1 {
        return api.burn(
            BurnRequest1(
                signerPayloadJSON = args.signerPayloadJSON,
                signature = args.signature
            ), RefungibleApi.Use_burn.submit
        )
    }

}