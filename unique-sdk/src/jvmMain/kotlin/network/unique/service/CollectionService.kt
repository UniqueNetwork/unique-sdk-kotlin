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

    fun getAddCollectionAdminMutationService(): MutationService<AddCollectionAdminBody>

    fun getAddToAllowListMutationService(): MutationService<AddToAllowListBody>

    fun getConfirmSponsorshipMutationService(): MutationService<ConfirmSponsorshipBody>

    fun getCreateCollectionMutationService(): MutationService<CreateCollectionBody>

    fun getDestroyCollectionMutationService(): MutationService<DestroyCollectionBody>

    fun getDeleteCollectionPropertiesMutationService(): MutationService<DeleteCollectionPropertiesBody>

    fun getRemoveCollectionAdminMutationService(): MutationService<RemoveCollectionAdminBody>

    fun getRemoveFromAllowListMutationService(): MutationService<RemoveFromAllowListBody>

    fun getRemoveSponsorshipMutationService(): MutationService<RemoveSponsorshipBody>

    fun getSetCollectionLimitsMutationService(): MutationService<SetCollectionLimitsBody>

    fun getSetCollectionPermissionsMutationService(): MutationService<SetCollectionPermissionsBody>

    fun getSetCollectionPropertiesMutationService(): MutationService<SetCollectionPropertiesBody>

    fun getSetPropertyPermissionsMutationService(): MutationService<SetPropertyPermissionsBody>

    fun getSetSponsorshipMutationService(): MutationService<SetSponsorshipBody>

    fun getSetTransfersEnabledMutationService(): MutationService<SetTransfersEnabledBody>

    fun getTransferCollectionMutationService(): MutationService<TransferCollectionBody>
}