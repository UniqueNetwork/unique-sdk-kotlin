package network.unique.service.impl

import network.unique.api.FungibleApi
import network.unique.model.BalanceResponse
import network.unique.model.FungibleCollectionInfoDto
import network.unique.service.FungibleService
import java.math.BigDecimal

class FungibleServiceImpl(basePath: String) : FungibleService {

    private val api: FungibleApi = FungibleApi(basePath)

    override fun getFungibleCollectionInfo(collectionId: BigDecimal, at: String): FungibleCollectionInfoDto {
        return api.fungibleControllerGetCollection(collectionId, at)
    }

    override fun getBalance(collectionId: BigDecimal, address: String, at: String): BalanceResponse {
        return api.fungibleControllerGetBalance(address, collectionId, at)
    }

}