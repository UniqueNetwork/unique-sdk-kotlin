package network.unique.remote.model

import kotlinx.serialization.Serializable
import network.unique.remote.serializer.BigDecimalNumericSerializer
import java.math.BigDecimal

@Serializable
data class ExtrinsicTransferDto(
    val from: String,
    val to: String,
    @Serializable(with = BigDecimalNumericSerializer::class)
    val amount: BigDecimal,
)
