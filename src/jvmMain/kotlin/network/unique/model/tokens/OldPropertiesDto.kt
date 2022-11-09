package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
data class OldPropertiesDto(
    val _old_schemaVersion: String,
    val _old_offchainSchema: String,
    val _old_constOnChainSchema: String,
    val _old_variableOnChainSchema: String,
)
