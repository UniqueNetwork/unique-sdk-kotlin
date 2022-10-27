package who.we.remote.model

import kotlinx.serialization.Serializable
import who.we.remote.serializer.BigDecimalNumericSerializer
import java.math.BigDecimal

@Serializable
data class BalanceTransferBody(
    val address: String,
    val destination: String,
    @Serializable(with = BigDecimalNumericSerializer::class)
    val amount: BigDecimal,
)
