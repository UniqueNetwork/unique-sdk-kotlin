package network.unique.service.impl

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.service.CollectionService
import network.unique.service.MutationService
import network.unique.service.impl.collection.*
import java.math.BigDecimal

class CollectionServiceImpl(basePath: String) : CollectionService {

    private val api: CollectionsApi = CollectionsApi(basePath)

    private val addCollectionAdminMutationService: MutationService<AddAdminRequest, AddAdminDefaultResponse> =
        AddCollectionAdminMutationServiceImpl(basePath)
    private val addToAllowLIstMutationService: MutationService<AddToAllowListRequest, AddToAllowListDefaultResponse> =
        AddToAllowListMutationServiceImpl(basePath)
    private val confirmSponsorshipMutationService: MutationService<ConfirmSponsorshipRequest, ConfirmSponsorshipDefaultResponse> =
        ConfirmSponsorshipMutationServiceImpl(basePath)
    private val createCollectionMutationService: MutationService<CreateCollectionMutationRequest, CreateCollectionMutationDefaultResponse> =
        CreateCollectionMutationServiceImpl(basePath)
    private val deleteCollectionPropertiesMutationService: MutationService<DeleteCollectionPropertiesRequest, DeleteCollectionPropertiesDefaultResponse> =
        DeleteCollectionPropertiesMutationServiceImpl(basePath)
    private val destroyCollectionMutationService: MutationService<DestroyRequest, DestroyDefaultResponse> =
        DestroyCollectionMutationServiceImpl(basePath)
    private val removeCollectionAdminMutationService: MutationService<RemoveAdminRequest, RemoveAdminDefaultResponse> =
        RemoveCollectionAdminMutationServiceImpl(basePath)
    private val removeFromAllowListMutationService: MutationService<RemoveFromAllowListRequest, RemoveFromAllowListDefaultResponse> =
        RemoveFromAllowListMutationServiceImpl(basePath)
    private val removeSponsorshipMutationService: MutationService<RemoveSponsorshipRequest, RemoveSponsorshipDefaultResponse> =
        RemoveSponsorshipMutationServiceImpl(basePath)
    private val setCollectionLimitsMutationService: MutationService<SetCollectionLimitsRequest, SetCollectionLimitsDefaultResponse> =
        SetCollectionLimitsMutationServiceImpl(basePath)
    private val setCollectionPermissionsMutationService: MutationService<SetPermissionsRequest, SetPermissionsDefaultResponse> =
        SetCollectionPermissionsMutationServiceImpl(basePath)
    private val setCollectionPropertiesMutationService: MutationService<SetCollectionPropertiesRequest, SetCollectionPropertiesDefaultResponse> =
        SetCollectionPropertiesMutationServiceImpl(basePath)
    private val setPropertyPermissionMutationService: MutationService<SetPropertyPermissionsRequest, SetPropertyPermissionsDefaultResponse> =
        SetPropertyPermissionsMutationServiceImpl(basePath)
    private val setSponsorshipMutationService: MutationService<SetSponsorshipRequest, SetSponsorshipDefaultResponse> =
        SetSponsorshipMutationServiceImpl(basePath)
    private val setTransfersEnabledMutationService: MutationService<SetTransfersEnabledRequest, SetTransfersEnabledDefaultResponse> =
        SetTransfersEnabledMutationServiceImpl(basePath)
    private val transferCollectionMutationService: MutationService<TransferRequest, TransferDefaultResponse> =
        TransferCollectionMutationServiceImpl(basePath)

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

    override fun getAddCollectionAdmin(): MutationService<AddAdminRequest, AddAdminDefaultResponse> {
        return addCollectionAdminMutationService
    }

    override fun getAddToAllowList(): MutationService<AddToAllowListRequest, AddToAllowListDefaultResponse> {
        return addToAllowLIstMutationService
    }

    override fun getConfirmSponsorship(): MutationService<ConfirmSponsorshipRequest, ConfirmSponsorshipDefaultResponse> {
        return confirmSponsorshipMutationService
    }

    override fun getCreateCollection(): MutationService<CreateCollectionMutationRequest, CreateCollectionMutationDefaultResponse> {
        return createCollectionMutationService
    }

    override fun getDestroyCollection(): MutationService<DestroyRequest, DestroyDefaultResponse> {
        return destroyCollectionMutationService
    }

    override fun getDeleteCollectionProperties(): MutationService<DeleteCollectionPropertiesRequest, DeleteCollectionPropertiesDefaultResponse> {
        return deleteCollectionPropertiesMutationService
    }

    override fun getRemoveCollectionAdmin(): MutationService<RemoveAdminRequest, RemoveAdminDefaultResponse> {
        return removeCollectionAdminMutationService
    }

    override fun getRemoveFromAllowList(): MutationService<RemoveFromAllowListRequest, RemoveFromAllowListDefaultResponse> {
        return removeFromAllowListMutationService
    }

    override fun getRemoveSponsorship(): MutationService<RemoveSponsorshipRequest, RemoveSponsorshipDefaultResponse> {
        return removeSponsorshipMutationService
    }

    override fun getSetCollectionLimits(): MutationService<SetCollectionLimitsRequest, SetCollectionLimitsDefaultResponse> {
        return setCollectionLimitsMutationService
    }

    override fun getSetCollectionPermissions(): MutationService<SetPermissionsRequest, SetPermissionsDefaultResponse> {
        return setCollectionPermissionsMutationService
    }

    override fun getSetCollectionProperties(): MutationService<SetCollectionPropertiesRequest, SetCollectionPropertiesDefaultResponse> {
        return setCollectionPropertiesMutationService
    }

    override fun getSetPropertyPermissions(): MutationService<SetPropertyPermissionsRequest, SetPropertyPermissionsDefaultResponse> {
        return setPropertyPermissionMutationService
    }

    override fun getSetSponsorship(): MutationService<SetSponsorshipRequest, SetSponsorshipDefaultResponse> {
        return setSponsorshipMutationService
    }

    override fun getSetTransfersEnabled(): MutationService<SetTransfersEnabledRequest, SetTransfersEnabledDefaultResponse> {
        return setTransfersEnabledMutationService
    }

    override fun getTransferCollection(): MutationService<TransferRequest, TransferDefaultResponse> {
        return transferCollectionMutationService
    }
}