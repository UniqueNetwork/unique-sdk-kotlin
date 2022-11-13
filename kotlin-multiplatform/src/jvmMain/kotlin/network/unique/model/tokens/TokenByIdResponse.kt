package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
data class TokenByIdResponse(
    val attributes: Array<DecodedAttributeDto>,
    val image: FileDto,
    val owner: String,
    val tokenId: Long,
    val audio: FileDto,
    val description: Map<String, String>,
    val name: Map<String, String>,
    val imagePreview: FileDto,
    val nestingParentToken: NestingParentId,
    val spatialObject: FileDto,
    val video: FileDto,
    val properties: Array<TokenProperty>,
    val collection: CollectionInfoWithSchemaResponse,
)
