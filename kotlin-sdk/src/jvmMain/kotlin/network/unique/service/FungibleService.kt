package network.unique.service

import network.unique.model.BalanceResponse
import network.unique.model.FungibleCollectionInfoDto
import java.math.BigDecimal

interface FungibleService {

    fun getFungibleCollectionInfo(collectionId: BigDecimal, at: String): FungibleCollectionInfoDto

    fun getBalance(collectionId: BigDecimal, address: String, at: String): BalanceResponse

}