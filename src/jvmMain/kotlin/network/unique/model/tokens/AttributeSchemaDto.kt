package network.unique.model.tokens

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class AttributeSchemaDto(
    val name: Map<String, String>,
    val optional: Boolean,
    val isArray: Boolean,
    val type: AttributeType,
    //TODO Can be Map<String, String> and Map<String, Number>
    val enumValues: JsonObject
)
