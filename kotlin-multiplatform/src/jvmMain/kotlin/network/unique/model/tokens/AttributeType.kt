package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
enum class AttributeType {
    integer,
    float,
    boolean,
    timestamp,
    string,
    url,
    isoDate,
    time,
    colorRgba,
}