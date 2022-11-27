package network.unique.service

import network.unique.model.*
import java.math.BigDecimal

interface FungibleService {

    fun getFungibleCollectionInfo(collectionId: BigDecimal, at: String): FungibleCollectionInfoDto

    fun getBalance(collectionId: BigDecimal, address: String, at: String): BalanceResponse

    fun getAddTokensMutationService(): MutationService<AddTokensArgsDto>

    fun getCreateFungibleCollectionMutationService(): MutationService<CreateFungibleCollectionRequest>

    fun getTransferTokensMutationService(): MutationService<TransferTokensArgsDto>

}