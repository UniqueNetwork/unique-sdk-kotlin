package network.unique.remote.model

import kotlinx.serialization.Serializable
import network.unique.remote.serializer.BigDecimalNumericSerializer
import java.math.BigDecimal

@Serializable
class FeeResponse(
    val raw: String,
    @Serializable(with = BigDecimalNumericSerializer::class)
    val amount: BigDecimal,
    val formatted: String,
    val unit: String,
    val decimals: Int,
)