package network.unique.model.extrinsic

import kotlinx.serialization.Serializable
import network.unique.serializer.BigDecimalNumericSerializer
import java.math.BigDecimal

@Serializable
data class ExtrinsicTransferDto(
    val from: String,
    val to: String,
    @Serializable(with = BigDecimalNumericSerializer::class)
    val amount: BigDecimal,
)
