package network.unique.service

import network.unique.model.*
import java.math.BigDecimal

interface FungibleService {

    fun getFungibleCollectionInfo(collectionId: BigDecimal, at: String): FungibleCollectionInfoDto

    fun getBalance(collectionId: BigDecimal, address: String, at: String): BalanceResponse

    fun getAddTokens(): MutationService<AddTokensMutationRequest, AddTokensMutationDefaultResponse>

    fun getCreateFungibleCollection(): MutationService<CreateFungibleCollectionMutationRequest, CreateCollectionMutationDefaultResponse>

    fun getTransferTokens(): MutationService<TransferTokensMutationRequest, TransferTokensMutationDefaultResponse>

}