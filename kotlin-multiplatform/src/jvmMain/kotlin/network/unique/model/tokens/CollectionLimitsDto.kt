package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
data class CollectionLimitsDto(
    val accountTokenOwnershipLimit: Long,
    val sponsoredDataSize: Long,
    val sponsoredDataRateLimit: Long,
    val tokenLimit: Long,
    val sponsorTransferTimeout: Long,
    val sponsorApproveTimeout: Long,
    val ownerCanTransfer: Boolean,
    val ownerCanDestroy: Boolean,
    val transfersEnabled: Boolean,
)
