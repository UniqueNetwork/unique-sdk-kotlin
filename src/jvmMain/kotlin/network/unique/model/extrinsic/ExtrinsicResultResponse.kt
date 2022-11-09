package network.unique.model.extrinsic

import kotlinx.serialization.Serializable
import network.unique.model.FeeResponse

@Serializable
data class ExtrinsicResultResponse(
    val status: String,
    val isCompleted: Boolean,
    val isError: Boolean,
    val blockHash: String? = null,
    val blockIndex: Long? = null,
    val error: String? = null,
    val events: Array<ExtrinsicResultEvent>,
    val parsed: ExtrinsicTransferDto? = null,
    val fee: FeeResponse? = null,
    val callbackUrl: String? = null,
    val useMethod: Boolean,
)