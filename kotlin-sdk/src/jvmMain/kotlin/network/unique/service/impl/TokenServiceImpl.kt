package network.unique.service.impl

import network.unique.api.TokensApi
import network.unique.model.*
import network.unique.service.TokenService
import java.math.BigDecimal

class TokenServiceImpl(basePath: String) : TokenService {

    private val api: TokensApi = TokensApi(basePath)

    override fun getToken(tokenId: BigDecimal, collectionId: BigDecimal, at: String): TokenByIdResponse {
        return api.newTokenControllerGetTokenNew(collectionId, tokenId, at)
    }

    override fun getTokenChildren(tokenId: BigDecimal, collectionId: BigDecimal, at: String): TokenChildrenResponse {
        return api.newTokenControllerTokenChildren(collectionId, tokenId, at)
    }

    override fun getTokenParent(tokenId: BigDecimal, collectionId: BigDecimal, at: String): TokenParentResponse {
        return api.newTokenControllerTokenParent(collectionId, tokenId, at)
    }

    override fun getTokenOwner(tokenId: BigDecimal, collectionId: BigDecimal, at: String): TokenOwnerResponse {
        return api.newTokenControllerTokenOwner(collectionId, tokenId, at)
    }

    override fun getTopmostTokenOwner(
        tokenId: BigDecimal,
        collectionId: BigDecimal,
        at: String
    ): TopmostTokenOwnerResponse {
        return api.newTokenControllerTopmostTokenOwner(collectionId, tokenId, at)
    }

    override fun tokenIsBundle(tokenId: BigDecimal, collectionId: BigDecimal, at: String): IsBundleResponse {
        return api.newTokenControllerIsBundle(collectionId, tokenId, at)
    }

    override fun getTokenBundle(tokenId: BigDecimal, collectionId: BigDecimal, at: String): GetBundleResponse {
        return api.newTokenControllerGetBundle(collectionId, tokenId, at)
    }

    override fun getTokenProperties(
        tokenId: BigDecimal,
        collectionId: BigDecimal,
        at: String
    ): TokenPropertiesResponse {
        return api.newTokenControllerTokenProperties(collectionId, tokenId, at)
    }

    override fun getAccountTokens(address: String, collectionId: BigDecimal, at: String): AccountTokensResponse {
        return api.newTokenControllerGetAccountTokens(address, collectionId, at)
    }

    override fun tokenIsExists(tokenId: BigDecimal, collectionId: BigDecimal, at: String): TokenExistsResponse {
        return api.newTokenControllerGetTokensExists(collectionId, tokenId, at)
    }

    override fun tokenIsAllowed(
        tokenId: BigDecimal,
        collectionId: BigDecimal,
        from: String,
        to: String,
        at: String
    ): AllowanceResultResponse {
        return api.newTokenControllerAllowance(collectionId, tokenId, from, to, at)
    }

    override fun getTokenBalance(
        tokenId: BigDecimal,
        collectionId: BigDecimal,
        address: String,
        at: String
    ): TokenBalanceResponse {
        return api.newTokenControllerGetBalance(collectionId, tokenId, address, at)
    }

}