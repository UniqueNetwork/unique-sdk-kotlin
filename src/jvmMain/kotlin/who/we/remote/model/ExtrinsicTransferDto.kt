package who.we.remote.model

import kotlinx.serialization.Serializable
import who.we.remote.serializer.BigDecimalNumericSerializer
import java.math.BigDecimal

@Serializable
data class ExtrinsicTransferDto(
    val from: String,
    val to: String,
    @Serializable(with = BigDecimalNumericSerializer::class)
    val amount: BigDecimal,
)
