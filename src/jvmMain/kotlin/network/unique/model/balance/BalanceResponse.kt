package network.unique.model.balance

import kotlinx.serialization.Serializable
import network.unique.serializer.BigDecimalNumericSerializer
import java.math.BigDecimal

@Serializable
data class BalanceResponse(
    val raw: String,
    val amount: String,
    val formatted: String,
    val unit: String,
    val decimals: String,
)
