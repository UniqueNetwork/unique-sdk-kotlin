package network.unique.model.tokens

import com.fasterxml.jackson.annotation.JsonRawValue
import kotlinx.serialization.Serializable

@Serializable
data class DecodedAttributeDto(
    val name: Map<String, String>,
    val type: String,
    val isArray: Boolean,
    val isEnum: Boolean,
    // TODO val rawValue:
    // TODO val value:
)
