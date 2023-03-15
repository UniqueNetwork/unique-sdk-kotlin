package network.unique.service

import network.unique.model.FeeResponse
import network.unique.model.SubmitResultResponse
import network.unique.model.SubmitTxBody
import network.unique.model.UnsignedTxPayloadResponse

abstract class MutationService<A, R> {

    abstract fun build(args: A): UnsignedTxPayloadResponse

    abstract fun getFee(args: A): FeeResponse

    abstract fun getFee(args: UnsignedTxPayloadResponse): FeeResponse

    abstract fun getFee(args: SubmitTxBody): FeeResponse

    abstract fun sign(args: A): SubmitTxBody

    abstract fun sign(args: UnsignedTxPayloadResponse): SubmitTxBody

    abstract fun submit(args: A): R

    abstract fun submit(args: UnsignedTxPayloadResponse): R

    abstract fun submit(args: SubmitTxBody): R

    abstract fun submitWatch(args: A): R

    abstract fun submitWatch(args: UnsignedTxPayloadResponse): R

    abstract fun submitWatch(args: SubmitTxBody): R

}