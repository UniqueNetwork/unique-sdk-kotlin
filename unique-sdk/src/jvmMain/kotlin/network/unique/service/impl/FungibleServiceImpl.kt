package network.unique.service.impl

import network.unique.api.FungibleApi
import network.unique.model.*
import network.unique.service.FungibleService
import network.unique.service.MutationService
import network.unique.service.impl.fungible.AddTokensMutationServiceImpl
import network.unique.service.impl.fungible.CreateFungibleCollectionMutationServiceImpl
import network.unique.service.impl.fungible.TransferTokensMutationServiceImpl
import java.math.BigDecimal

class FungibleServiceImpl(basePath: String) : FungibleService {

    private val api: FungibleApi = FungibleApi(basePath)

    private val addTokensMutationService: MutationService<AddTokensArgsDto> = AddTokensMutationServiceImpl(basePath)
    private val createFungibleCollectionMutationService: MutationService<CreateFungibleCollectionRequest> =
        CreateFungibleCollectionMutationServiceImpl(basePath)
    private val transferTokensMutationService: MutationService<TransferTokensArgsDto> =
        TransferTokensMutationServiceImpl(basePath)

    override fun getFungibleCollectionInfo(collectionId: BigDecimal, at: String): FungibleCollectionInfoDto {
        return api.fungibleControllerGetCollection(collectionId, at)
    }

    override fun getBalance(collectionId: BigDecimal, address: String, at: String): BalanceResponse {
        return api.fungibleControllerGetBalance(address, collectionId, at)
    }

    override fun getAddTokensMutationService(): MutationService<AddTokensArgsDto> {
        return addTokensMutationService
    }

    override fun getCreateFungibleCollectionMutationService(): MutationService<CreateFungibleCollectionRequest> {
        return createFungibleCollectionMutationService
    }

    override fun getTransferTokensMutationService(): MutationService<TransferTokensArgsDto> {
        return transferTokensMutationService
    }

}