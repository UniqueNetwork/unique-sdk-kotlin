package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
data class FileDto(
    val fullUrl: String,
    val urlInfix: String,
    val url: String,
    val ipfsCid: String,
    val hash: String,
)
