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

    fun getAddCollectionAdmin(): MutationService<AddAdminRequest, AddAdminDefaultResponse>

    fun getAddToAllowList(): MutationService<AddToAllowListRequest, AddToAllowListDefaultResponse>

    fun getConfirmSponsorship(): MutationService<ConfirmSponsorshipRequest, ConfirmSponsorshipDefaultResponse>

    fun getCreateCollection(): MutationService<CreateCollectionMutationRequest, CreateCollectionMutationDefaultResponse>

    fun getDestroyCollection(): MutationService<DestroyRequest, DestroyDefaultResponse>

    fun getDeleteCollectionProperties(): MutationService<DeleteCollectionPropertiesRequest, DeleteCollectionPropertiesDefaultResponse>

    fun getRemoveCollectionAdmin(): MutationService<RemoveAdminRequest, RemoveAdminDefaultResponse>

    fun getRemoveFromAllowList(): MutationService<RemoveFromAllowListRequest, RemoveFromAllowListDefaultResponse>

    fun getRemoveSponsorship(): MutationService<RemoveSponsorshipRequest, RemoveSponsorshipDefaultResponse>

    fun getSetCollectionLimits(): MutationService<SetCollectionLimitsRequest, SetCollectionLimitsDefaultResponse>

    fun getSetCollectionPermissions(): MutationService<SetPermissionsRequest, SetPermissionsDefaultResponse>

    fun getSetCollectionProperties(): MutationService<SetCollectionPropertiesRequest, SetCollectionPropertiesDefaultResponse>

    fun getSetPropertyPermissions(): MutationService<SetPropertyPermissionsRequest, SetPropertyPermissionsDefaultResponse>

    fun getSetSponsorship(): MutationService<SetSponsorshipRequest, SetSponsorshipDefaultResponse>

    fun getSetTransfersEnabled(): MutationService<SetTransfersEnabledRequest, SetTransfersEnabledDefaultResponse>

    fun getTransferCollection(): MutationService<TransferRequest, TransferDefaultResponse>
}