package network.unique.service

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import network.unique.exception.RequestException
import network.unique.model.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

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