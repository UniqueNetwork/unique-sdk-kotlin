package who.we.remote.model

import kotlinx.serialization.Serializable

@Serializable
class ExtrinsicResultResponse(
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
)