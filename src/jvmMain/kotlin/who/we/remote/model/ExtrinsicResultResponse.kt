package who.we.remote.model

import kotlinx.serialization.Serializable

@Serializable
class ExtrinsicResultResponse(
    val status: String,
    val isCompleted: Boolean,
    val isError: Boolean,
    val blockHash: String,
    val blockIndex: Long,
    val error: String,
    val events: Array<ExtrinsicResultEvent>,
    val parsed: BalanceTransferBody,
    val fee: FeeResponse,
    val callbackUrl: String,
)