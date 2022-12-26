package network.unique.service.impl

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.service.CollectionService
import network.unique.service.MutationService
import network.unique.service.impl.collection.*
import java.math.BigDecimal

class CollectionServiceImpl(basePath: String) : CollectionService {

    private val api: CollectionsApi = CollectionsApi(basePath)

    private val addCollectionAdminMutationService: MutationService<AddCollectionAdminBody> =
        AddCollectionAdminMutationServiceImpl(basePath)
    private val addToAllowLIstMutationService: MutationService<AddToAllowListBody> =
        AddToAllowListMutationServiceImpl(basePath)
    private val confirmSponsorshipMutationService: MutationService<ConfirmSponsorshipBody> =
        ConfirmSponsorshipMutationServiceImpl(basePath)
    private val createCollectionMutationService: MutationService<CreateCollectionBody> =
        CreateCollectionMutationServiceImpl(basePath)
    private val deleteCollectionPropertiesMutationService: MutationService<DeleteCollectionPropertiesBody> =
        DeleteCollectionPropertiesMutationServiceImpl(basePath)
    private val destroyCollectionMutationService: MutationService<DestroyCollectionBody> =
        DestroyCollectionMutationServiceImpl(basePath)
    private val removeCollectionAdminMutationService: MutationService<RemoveCollectionAdminBody> =
        RemoveCollectionAdminMutationServiceImpl(basePath)
    private val removeFromAllowListMutationService: MutationService<RemoveFromAllowListBody> =
        RemoveFromAllowListMutationServiceImpl(basePath)
    private val removeSponsorshipMutationService: MutationService<RemoveSponsorshipBody> =
        RemoveSponsorshipMutationServiceImpl(basePath)
    private val setCollectionLimitsMutationService: MutationService<SetCollectionLimitsBody> =
        SetCollectionLimitsMutationServiceImpl(basePath)
    private val setCollectionPermissionsMutationService: MutationService<SetCollectionPermissionsBody> =
        SetCollectionPermissionsMutationServiceImpl(basePath)
    private val setCollectionPropertiesMutationService: MutationService<SetCollectionPropertiesBody> =
        SetCollectionPropertiesMutationServiceImpl(basePath)
    private val setPropertyPermissionMutationService: MutationService<SetPropertyPermissionsBody> =
        SetPropertyPermissionsMutationServiceImpl(basePath)
    private val setSponsorshipMutationService: MutationService<SetSponsorshipBody> =
        SetSponsorshipMutationServiceImpl(basePath)
    private val setTransfersEnabledMutationService: MutationService<SetTransfersEnabledBody> =
        SetTransfersEnabledMutationServiceImpl(basePath)
    private val transferCollectionMutationService: MutationService<TransferCollectionBody> =
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

    override fun getAddCollectionAdmin(): MutationService<AddCollectionAdminBody> {
        return addCollectionAdminMutationService
    }

    override fun getAddToAllowList(): MutationService<AddToAllowListBody> {
        return addToAllowLIstMutationService
    }

    override fun getConfirmSponsorship(): MutationService<ConfirmSponsorshipBody> {
        return confirmSponsorshipMutationService
    }

    override fun getCreateCollection(): MutationService<CreateCollectionBody> {
        return createCollectionMutationService
    }

    override fun getDestroyCollection(): MutationService<DestroyCollectionBody> {
        return destroyCollectionMutationService
    }

    override fun getDeleteCollectionProperties(): MutationService<DeleteCollectionPropertiesBody> {
        return deleteCollectionPropertiesMutationService
    }

    override fun getRemoveCollectionAdmin(): MutationService<RemoveCollectionAdminBody> {
        return removeCollectionAdminMutationService
    }

    override fun getRemoveFromAllowList(): MutationService<RemoveFromAllowListBody> {
        return removeFromAllowListMutationService
    }

    override fun getRemoveSponsorship(): MutationService<RemoveSponsorshipBody> {
        return removeSponsorshipMutationService
    }

    override fun getSetCollectionLimits(): MutationService<SetCollectionLimitsBody> {
        return setCollectionLimitsMutationService
    }

    override fun getSetCollectionPermissions(): MutationService<SetCollectionPermissionsBody> {
        return setCollectionPermissionsMutationService
    }

    override fun getSetCollectionProperties(): MutationService<SetCollectionPropertiesBody> {
        return setCollectionPropertiesMutationService
    }

    override fun getSetPropertyPermissions(): MutationService<SetPropertyPermissionsBody> {
        return setPropertyPermissionMutationService
    }

    override fun getSetSponsorship(): MutationService<SetSponsorshipBody> {
        return setSponsorshipMutationService
    }

    override fun getSetTransfersEnabled(): MutationService<SetTransfersEnabledBody> {
        return setTransfersEnabledMutationService
    }

    override fun getTransferCollection(): MutationService<TransferCollectionBody> {
        return transferCollectionMutationService
    }
}