package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
data class CollectionFlags(
    val foreign: Boolean,
    val erc721metadata: Boolean,
)
