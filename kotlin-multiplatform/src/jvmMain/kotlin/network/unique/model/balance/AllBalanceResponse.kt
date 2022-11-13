package network.unique.model.balance

import kotlinx.serialization.Serializable
import network.unique.serializer.BigDecimalNumericSerializer
import java.math.BigDecimal

@Serializable
data class AllBalanceResponse(
    val availableBalance: BalanceResponse,
    val lockedBalance: BalanceResponse,
    val freeBalance: BalanceResponse,
    val address: String,
)
