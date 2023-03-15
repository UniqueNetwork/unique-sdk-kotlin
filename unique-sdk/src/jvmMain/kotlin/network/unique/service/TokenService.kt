package network.unique.service

import network.unique.model.*
import java.math.BigDecimal

interface TokenService {

    fun getToken(tokenId: BigDecimal, collectionId: BigDecimal, at: String): TokenByIdResponse

    fun getTokenChildren(tokenId: BigDecimal, collectionId: BigDecimal, at: String): TokenChildrenResponse

    fun getTokenParent(tokenId: BigDecimal, collectionId: BigDecimal, at: String): TokenParentResponse

    fun getTokenOwner(tokenId: BigDecimal, collectionId: BigDecimal, at: String): TokenOwnerResponse

    fun getTopmostTokenOwner(tokenId: BigDecimal, collectionId: BigDecimal, at: String): TopmostTokenOwnerResponse

    fun tokenIsBundle(tokenId: BigDecimal, collectionId: BigDecimal, at: String): IsBundleResponse

    fun getTokenBundle(tokenId: BigDecimal, collectionId: BigDecimal, at: String): GetBundleResponse

    fun getTokenProperties(tokenId: BigDecimal, collectionId: BigDecimal, at: String): TokenPropertiesResponse

    fun getAccountTokens(address: String, collectionId: BigDecimal, at: String): AccountTokensResponse

    fun tokenIsExists(tokenId: BigDecimal, collectionId: BigDecimal, at: String): TokenExistsResponse

    fun tokenIsAllowed(
        tokenId: BigDecimal,
        collectionId: BigDecimal,
        from: String,
        to: String,
        at: String
    ): AllowanceResultResponse

    fun getTokenBalance(
        tokenId: BigDecimal,
        collectionId: BigDecimal,
        address: String,
        at: String
    ): TokenBalanceResponse

    fun getApproveToken(): MutationService<ApproveRequest, ApproveDefaultResponse>

    fun getBurnToken(): MutationService<BurnTokenRequest, BurnTokenDefaultResponse>

    fun getCreateMultipleTokens(): MutationService<CreateMultipleTokensMutationRequest, CreateNewTokenMutationDefaultResponse>

    fun getCreateToken(): MutationService<CreateNewTokenMutationRequest, CreateNewTokenMutationDefaultResponse>

    fun getDeleteTokenProperties(): MutationService<DeleteTokenPropertiesRequest, DeleteTokenPropertiesDefaultResponse>

    fun getNestToken(): MutationService<NestTokenRequest, NestTokenDefaultResponse>

    fun getSetTokenProperties(): MutationService<SetTokenPropertiesRequest, SetTokenPropertiesDefaultResponse>

    fun getTransferToken(): MutationService<TransferTokenRequest, TransferTokenDefaultResponse>

    fun getUnnestToken(): MutationService<UnnestTokenRequest, UnnestTokenDefaultResponse>

}