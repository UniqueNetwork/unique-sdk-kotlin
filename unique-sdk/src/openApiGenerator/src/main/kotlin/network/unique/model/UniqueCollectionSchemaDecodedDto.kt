/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package network.unique.model

import network.unique.model.AttributeSchemaDto
import network.unique.model.AudioDto
import network.unique.model.ImageDto
import network.unique.model.ImagePreviewDto
import network.unique.model.OldPropertiesDto
import network.unique.model.SpatialObjectDto
import network.unique.model.UniqueCollectionSchemaDecodedDtoCoverPicture
import network.unique.model.VideoDto

import com.squareup.moshi.Json

/**
 * 
 *
 * @param collectionId 
 * @param coverPicture 
 * @param image 
 * @param schemaName 
 * @param schemaVersion 
 * @param oldProperties 
 * @param audio 
 * @param spatialObject 
 * @param video 
 * @param file 
 * @param attributesSchema 
 * @param attributesSchemaVersion 
 * @param coverPicturePreview 
 * @param imagePreview 
 */


data class UniqueCollectionSchemaDecodedDto (

    @Json(name = "collectionId")
    val collectionId: java.math.BigDecimal? = null,

    @Json(name = "coverPicture")
    val coverPicture: UniqueCollectionSchemaDecodedDtoCoverPicture? = null,

    @Json(name = "image")
    val image: ImageDto? = null,

    @Json(name = "schemaName")
    val schemaName: UniqueCollectionSchemaDecodedDto.SchemaName? = null,

    @Json(name = "schemaVersion")
    val schemaVersion: kotlin.String? = null,

    @Json(name = "oldProperties")
    val oldProperties: OldPropertiesDto? = null,

    @Json(name = "audio")
    val audio: AudioDto? = null,

    @Json(name = "spatialObject")
    val spatialObject: SpatialObjectDto? = null,

    @Json(name = "video")
    val video: VideoDto? = null,

    @Json(name = "file")
    val file: ImageDto? = null,

    @Json(name = "attributesSchema")
    val attributesSchema: kotlin.collections.Map<kotlin.String, AttributeSchemaDto>? = null,

    @Json(name = "attributesSchemaVersion")
    val attributesSchemaVersion: kotlin.String? = null,

    @Json(name = "coverPicturePreview")
    val coverPicturePreview: UniqueCollectionSchemaDecodedDtoCoverPicture? = null,

    @Json(name = "imagePreview")
    val imagePreview: ImagePreviewDto? = null

) {

    /**
     * 
     *
     * Values: unique,old,eRC721Metadata
     */
    enum class SchemaName(val value: kotlin.String) {
        @Json(name = "unique") unique("unique"),
        @Json(name = "_old_") old("_old_"),
        @Json(name = "ERC721Metadata") eRC721Metadata("ERC721Metadata");
    }
}

