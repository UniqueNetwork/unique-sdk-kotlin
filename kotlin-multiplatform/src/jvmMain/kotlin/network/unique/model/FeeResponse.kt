package network.unique.model

import kotlinx.serialization.Serializable
import network.unique.serializer.BigDecimalNumericSerializer
import java.math.BigDecimal

@Serializable
data class FeeResponse(
    val raw: String,
    @Serializable(with = BigDecimalNumericSerializer::class)
    val amount: BigDecimal,
    val formatted: String,
    val unit: String,
    val decimals: Int,
)