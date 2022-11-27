package network.unique.service

import network.unique.model.FeeResponse
import network.unique.model.SubmitResultResponse
import network.unique.model.SubmitTxBody
import network.unique.model.UnsignedTxPayloadResponse

abstract class MutationService<A> {

    abstract fun build(args: A): UnsignedTxPayloadResponse

    abstract fun getFee(args: A): FeeResponse

    abstract fun getFee(args: UnsignedTxPayloadResponse): FeeResponse

    abstract fun getFee(args: SubmitTxBody): FeeResponse

    abstract fun sign(args: A, seed: String): SubmitTxBody

    abstract fun sign(args: UnsignedTxPayloadResponse, seed: String): SubmitTxBody

    abstract fun submit(args: A, seed: String): SubmitResultResponse

    abstract fun submit(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse

    abstract fun submit(args: SubmitTxBody): SubmitResultResponse

    abstract fun submitWatch(args: A, seed: String): SubmitResultResponse

    abstract fun submitWatch(args: UnsignedTxPayloadResponse, seed: String): SubmitResultResponse

    abstract fun submitWatch(args: SubmitTxBody): SubmitResultResponse

    protected fun toByteArray(data: String): ByteArray {
        return data.chunked(2)
            .map { it.toInt(16).toByte() }
            .toByteArray()
    }

}