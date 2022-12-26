package network.unique.service

import network.unique.model.*
import java.math.BigDecimal

interface CollectionService {

    fun getCollections(collectionId: BigDecimal, at: String): CollectionInfoWithSchemaResponse

    fun getStats(at: String): GetStatsResponse

    fun getLimits(collectionId: BigDecimal, at: String): EffectiveCollectionLimitsResponse

    fun getProperties(collectionId: BigDecimal, at: String): CollectionPropertiesResponse

    fun getCollectionTokens(collectionId: BigDecimal, at: String): GetCollectionTokensResponse

    fun getCollectionPropertyPermissions(
        collectionId: BigDecimal,
        propertyKeys: List<String>,
        at: String
    ): PropertyPermissionsResponse

    fun getCollectionNextSponsored(
        collectionId: BigDecimal,
        address: String,
        tokenId: BigDecimal,
        at: String
    ): NextSponsoredResponse

    fun getCollectionLastTokenId(collectionId: BigDecimal, at: String): LastTokenIdResultDto

    fun getCollectionAllowList(collectionId: BigDecimal, at: String): AllowListBodyResult

    fun getCollectionAllowed(collectionId: BigDecimal, account: String, at: String): AllowedResponse

    fun getCollectionAdmins(collectionId: BigDecimal, at: String): AdminlistResponse

    fun getTotalSupply(collectionId: BigDecimal, at: String): TotalSupplyResponse

    fun getAddCollectionAdmin(): MutationService<AddCollectionAdminBody>

    fun getAddToAllowList(): MutationService<AddToAllowListBody>

    fun getConfirmSponsorship(): MutationService<ConfirmSponsorshipBody>

    fun getCreateCollection(): MutationService<CreateCollectionBody>

    fun getDestroyCollection(): MutationService<DestroyCollectionBody>

    fun getDeleteCollectionProperties(): MutationService<DeleteCollectionPropertiesBody>

    fun getRemoveCollectionAdmin(): MutationService<RemoveCollectionAdminBody>

    fun getRemoveFromAllowList(): MutationService<RemoveFromAllowListBody>

    fun getRemoveSponsorship(): MutationService<RemoveSponsorshipBody>

    fun getSetCollectionLimits(): MutationService<SetCollectionLimitsBody>

    fun getSetCollectionPermissions(): MutationService<SetCollectionPermissionsBody>

    fun getSetCollectionProperties(): MutationService<SetCollectionPropertiesBody>

    fun getSetPropertyPermissions(): MutationService<SetPropertyPermissionsBody>

    fun getSetSponsorship(): MutationService<SetSponsorshipBody>

    fun getSetTransfersEnabled(): MutationService<SetTransfersEnabledBody>

    fun getTransferCollection(): MutationService<TransferCollectionBody>
}