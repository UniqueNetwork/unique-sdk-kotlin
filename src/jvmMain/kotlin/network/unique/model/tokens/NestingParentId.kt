package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
data class NestingParentId(
    val collectionId: Long,
    val tokenId: Long,
    val url: String,
    val ipfsCid: String,
    val hash: String,
)
