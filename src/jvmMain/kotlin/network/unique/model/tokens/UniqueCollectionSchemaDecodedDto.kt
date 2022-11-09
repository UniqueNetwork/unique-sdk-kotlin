package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
data class UniqueCollectionSchemaDecodedDto(
    val attributesSchema: Map<String, AttributeSchemaDto>,
    val attributesSchemaVersion: String,
    val collectionId: String,
    val coverPicture: FileDto,
    val image: ImageDto,
    val schemaName: String,
    val schemaVersion: String,
    val oldProperties: OldPropertiesDto,
    val coverPicturePreview: FileDto,
    val imagePreview: ImagePreviewDto,
    val audio: AudioDto,
    val spatialObject: SpatialObjectDto,
    val video: VideoDto,
)
