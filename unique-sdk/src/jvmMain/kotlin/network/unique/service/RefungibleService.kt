package network.unique.service

import network.unique.model.*
import java.math.BigDecimal

interface RefungibleService {

    fun getRefungibleCollectionInfo(collectionId: BigDecimal, at: String): CollectionInfoWithSchemaResponse

    fun getRefungibleTokensBalance(
        collectionId: BigDecimal,
        tokenId: BigDecimal,
        address: String,
        at: String
    ): TokenBalanceResponse

    fun getRefungibleTokenAllowance(
        collectionId: BigDecimal,
        tokenId: BigDecimal,
        from: String,
        to: String,
        at: String
    ): AllowanceRefungibleTokenResponse

    fun getTotalPieces(collectionId: BigDecimal, tokenId: BigDecimal): TotalPiecesResponse

    fun getAccountRefungibleTokens(
        collectionId: BigDecimal,
        address: String,
        at: String
    ): AccountRefungibleTokensResponse

    fun getCreateRefungibleCollection(
        collectionId: BigDecimal,
        address: String,
        at: String
    ): MutationService<CreateRefungibleCollectionMutationRequest, CreateCollectionMutationDefaultResponse>

    fun getBurnRefungibleCollection(): MutationService<BurnRequest1, BurnDefaultResponse1>

    fun getCreateRefungibleTokens(): MutationService<AddTokensMutationRequest1, AddTokensMutationDefaultResponse1>

    fun getTransferRefungibleTokens(): MutationService<TransferTokensMutationRequest1, TransferTokensMutationDefaultResponse1>

    fun getApproveRefungibleTokens(): MutationService<ApproveTokensMutationRequest1, ApproveTokensMutationDefaultResponse1>

    fun getRepartitionTokens(): MutationService<RepartitionTokenMutationRequest, RepartitionTokenMutationDefaultResponse>

}