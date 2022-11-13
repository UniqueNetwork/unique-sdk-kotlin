package network.unique.model.balance

import kotlinx.serialization.Serializable
import network.unique.serializer.BigDecimalNumericSerializer
import java.math.BigDecimal

@Serializable
data class BalanceTransferBody(
    val address: String,
    val destination: String,
    @Serializable(with = BigDecimalNumericSerializer::class)
    val amount: BigDecimal,
)
