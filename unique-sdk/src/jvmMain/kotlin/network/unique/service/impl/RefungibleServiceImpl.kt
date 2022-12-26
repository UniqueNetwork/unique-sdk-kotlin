package network.unique.service.impl

import network.unique.api.RefungibleApi
import network.unique.model.*
import network.unique.service.MutationService
import network.unique.service.RefungibleService
import network.unique.service.impl.refungible.*
import java.math.BigDecimal

class RefungibleServiceImpl(basePath: String) : RefungibleService {

    private val api: RefungibleApi = RefungibleApi(basePath)

    private val createRefungibleCollection: MutationService<CreateRefungibleCollectionMutationRequest> =
        CreateRefungibleCollectionMutationServiceImpl(basePath)
    private val burnRefungibleCollection: MutationService<BurnRequest1> = BurnRefungibleMutationServiceImpl(basePath)
    private val createRefungibleTokens: MutationService<AddTokensMutationRequest1> =
        CreateRefungibleTokensMutationServiceImpl(basePath)
    private val transferRefungibleTokens: MutationService<TransferTokensMutationRequest1> =
        TransferRefungibleTokensMutationServiceImpl(basePath)
    private val approveRefungibleTokens: MutationService<ApproveTokensMutationRequest1> =
        ApproveRefungibleTokensMutationServiceImpl(basePath)
    private val repartitionTokens: MutationService<RepartitionTokenMutationRequest> =
        RepartitionTokensMutationServiceImpl(basePath)

    override fun getRefungibleCollectionInfo(collectionId: BigDecimal, at: String): CollectionInfoWithSchemaResponse {
        return api.refungibleControllerGetCollection(collectionId, at)
    }

    override fun getRefungibleTokensBalance(
        collectionId: BigDecimal,
        tokenId: BigDecimal,
        address: String,
        at: String
    ): TokenBalanceResponse {
        return api.refungibleControllerGetBalance(collectionId, tokenId, address, at)
    }

    override fun getRefungibleTokenAllowance(
        collectionId: BigDecimal,
        tokenId: BigDecimal,
        from: String,
        to: String,
        at: String
    ): AllowanceRefungibleTokenResponse {
        return api.refungibleControllerAllowanceTokens(collectionId, tokenId, from, to, at)
    }

    override fun getTotalPieces(collectionId: BigDecimal, tokenId: BigDecimal): TotalPiecesResponse {
        return api.refungibleControllerTotalPieces(collectionId, tokenId)
    }

    override fun getAccountRefungibleTokens(
        collectionId: BigDecimal,
        address: String,
        at: String
    ): AccountRefungibleTokensResponse {
        return api.refungibleControllerGetAccountTokens(collectionId, address, at)
    }

    override fun getCreateRefungibleCollection(
        collectionId: BigDecimal,
        address: String,
        at: String
    ): MutationService<CreateRefungibleCollectionMutationRequest> {
        return createRefungibleCollection
    }

    override fun getBurnRefungibleCollection(): MutationService<BurnRequest1> {
        return burnRefungibleCollection
    }

    override fun getCreateRefungibleTokens(): MutationService<AddTokensMutationRequest1> {
        return createRefungibleTokens
    }

    override fun getTransferRefungibleTokens(): MutationService<TransferTokensMutationRequest1> {
        return transferRefungibleTokens
    }

    override fun getApproveRefungibleTokens(): MutationService<ApproveTokensMutationRequest1> {
        return approveRefungibleTokens
    }

    override fun getRepartitionTokens(): MutationService<RepartitionTokenMutationRequest> {
        return repartitionTokens
    }

}