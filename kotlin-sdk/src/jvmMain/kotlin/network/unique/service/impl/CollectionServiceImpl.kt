package network.unique.service.impl

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.service.CollectionService
import java.math.BigDecimal

class CollectionServiceImpl(basePath: String) : CollectionService {

    private val api: CollectionsApi = CollectionsApi(basePath)

    override fun getCollections(collectionId: BigDecimal, at: String): CollectionInfoWithSchemaResponse {
        return api.collectionControllerGetCollection(collectionId, at)
    }

    override fun getStats(at: String): GetStatsResponse {
        return api.collectionControllerStats(at)
    }

    override fun getLimits(collectionId: BigDecimal, at: String): EffectiveCollectionLimitsResponse {
        return api.collectionControllerEffectiveCollectionLimits(collectionId, at)
    }

    override fun getProperties(collectionId: BigDecimal, at: String): CollectionPropertiesResponse {
        return api.collectionControllerCollectionProperties(collectionId, at)
    }

    override fun getCollectionTokens(collectionId: BigDecimal, at: String): GetCollectionTokensResponse {
        return api.collectionControllerCollectionTokens(collectionId, at)
    }

    override fun getCollectionPropertyPermissions(
        collectionId: BigDecimal,
        propertyKeys: List<String>,
        at: String
    ): PropertyPermissionsResponse {
        return api.collectionControllerPropertyPermissions(collectionId, propertyKeys, at)
    }

    override fun getCollectionNextSponsored(
        collectionId: BigDecimal,
        address: String,
        tokenId: BigDecimal,
        at: String
    ): NextSponsoredResponse {
        return api.collectionControllerNextSponsored(collectionId, address, tokenId, at)
    }

    override fun getCollectionLastTokenId(collectionId: BigDecimal, at: String): LastTokenIdResultDto {
        return api.collectionControllerLastTokenId(collectionId, at)
    }

    override fun getCollectionAllowList(collectionId: BigDecimal, at: String): AllowListBodyResult {
        return api.collectionControllerAllowList(collectionId, at)
    }

    override fun getCollectionAllowed(collectionId: BigDecimal, account: String, at: String): AllowedResponse {
        return api.collectionControllerAllowed(collectionId, account, at)
    }

    override fun getCollectionAdmins(collectionId: BigDecimal, at: String): AdminlistResponse {
        return api.collectionControllerAdmins(collectionId, at)
    }

    override fun getTotalSupply(collectionId: BigDecimal, at: String): TotalSupplyResponse {
        return api.collectionControllerTotalSupply(collectionId, at)
    }
}