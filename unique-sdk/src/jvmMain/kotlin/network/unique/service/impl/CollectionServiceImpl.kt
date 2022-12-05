package network.unique.service.impl

import network.unique.api.CollectionsApi
import network.unique.model.*
import network.unique.service.CollectionService
import network.unique.service.MutationService
import network.unique.service.impl.collection.*
import java.math.BigDecimal

class CollectionServiceImpl(signerWrapper: SignerWrapper, basePath: String) : CollectionService {

    private val api: CollectionsApi = CollectionsApi(basePath)

    private val addCollectionAdminMutationService: MutationService<AddCollectionAdminBody> =
        AddCollectionAdminMutationServiceImpl(signerWrapper, basePath)
    private val addToAllowLIstMutationService: MutationService<AddToAllowListBody> =
        AddToAllowListMutationServiceImpl(signerWrapper, basePath)
    private val confirmSponsorshipMutationService: MutationService<ConfirmSponsorshipBody> =
        ConfirmSponsorshipMutationServiceImpl(signerWrapper, basePath)
    private val createCollectionMutationService: MutationService<CreateCollectionBody> =
        CreateCollectionMutationServiceImpl(signerWrapper, basePath)
    private val deleteCollectionPropertiesMutationService: MutationService<DeleteCollectionPropertiesBody> =
        DeleteCollectionPropertiesMutationServiceImpl(signerWrapper, basePath)
    private val destroyCollectionMutationService: MutationService<DestroyCollectionBody> =
        DestroyCollectionMutationServiceImpl(signerWrapper, basePath)
    private val removeCollectionAdminMutationService: MutationService<RemoveCollectionAdminBody> =
        RemoveCollectionAdminMutationServiceImpl(signerWrapper, basePath)
    private val removeFromAllowListMutationService: MutationService<RemoveFromAllowListBody> =
        RemoveFromAllowListMutationServiceImpl(signerWrapper, basePath)
    private val removeSponsorshipMutationService: MutationService<RemoveSponsorshipBody> =
        RemoveSponsorshipMutationServiceImpl(signerWrapper, basePath)
    private val setCollectionLimitsMutationService: MutationService<SetCollectionLimitsBody> =
        SetCollectionLimitsMutationServiceImpl(signerWrapper, basePath)
    private val setCollectionPermissionsMutationService: MutationService<SetCollectionPermissionsBody> =
        SetCollectionPermissionsMutationServiceImpl(signerWrapper, basePath)
    private val setCollectionPropertiesMutationService: MutationService<SetCollectionPropertiesBody> =
        SetCollectionPropertiesMutationServiceImpl(signerWrapper, basePath)
    private val setPropertyPermissionMutationService: MutationService<SetPropertyPermissionsBody> =
        SetPropertyPermissionsMutationServiceImpl(signerWrapper, basePath)
    private val setSponsorshipMutationService: MutationService<SetSponsorshipBody> =
        SetSponsorshipMutationServiceImpl(signerWrapper, basePath)
    private val setTransfersEnabledMutationService: MutationService<SetTransfersEnabledBody> =
        SetTransfersEnabledMutationServiceImpl(signerWrapper, basePath)
    private val transferCollectionMutationService: MutationService<TransferCollectionBody> =
        TransferCollectionMutationServiceImpl(signerWrapper, basePath)

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

    override fun getAddCollectionAdminMutationService(): MutationService<AddCollectionAdminBody> {
        return addCollectionAdminMutationService
    }

    override fun getAddToAllowListMutationService(): MutationService<AddToAllowListBody> {
        return addToAllowLIstMutationService
    }

    override fun getConfirmSponsorshipMutationService(): MutationService<ConfirmSponsorshipBody> {
        return confirmSponsorshipMutationService
    }

    override fun getCreateCollectionMutationService(): MutationService<CreateCollectionBody> {
        return createCollectionMutationService
    }

    override fun getDestroyCollectionMutationService(): MutationService<DestroyCollectionBody> {
        return destroyCollectionMutationService
    }

    override fun getDeleteCollectionPropertiesMutationService(): MutationService<DeleteCollectionPropertiesBody> {
        return deleteCollectionPropertiesMutationService
    }

    override fun getRemoveCollectionAdminMutationService(): MutationService<RemoveCollectionAdminBody> {
        return removeCollectionAdminMutationService
    }

    override fun getRemoveFromAllowListMutationService(): MutationService<RemoveFromAllowListBody> {
        return removeFromAllowListMutationService
    }

    override fun getRemoveSponsorshipMutationService(): MutationService<RemoveSponsorshipBody> {
        return removeSponsorshipMutationService
    }

    override fun getSetCollectionLimitsMutationService(): MutationService<SetCollectionLimitsBody> {
        return setCollectionLimitsMutationService
    }

    override fun getSetCollectionPermissionsMutationService(): MutationService<SetCollectionPermissionsBody> {
        return setCollectionPermissionsMutationService
    }

    override fun getSetCollectionPropertiesMutationService(): MutationService<SetCollectionPropertiesBody> {
        return setCollectionPropertiesMutationService
    }

    override fun getSetPropertyPermissionsMutationService(): MutationService<SetPropertyPermissionsBody> {
        return setPropertyPermissionMutationService
    }

    override fun getSetSponsorshipMutationService(): MutationService<SetSponsorshipBody> {
        return setSponsorshipMutationService
    }

    override fun getSetTransfersEnabledMutationService(): MutationService<SetTransfersEnabledBody> {
        return setTransfersEnabledMutationService
    }

    override fun getTransferCollectionMutationService(): MutationService<TransferCollectionBody> {
        return transferCollectionMutationService
    }
}