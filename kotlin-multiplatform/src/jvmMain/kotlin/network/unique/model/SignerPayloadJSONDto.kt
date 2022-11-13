package network.unique.model

import kotlinx.serialization.Serializable

@Serializable
data class SignerPayloadJSONDto(
    val address: String,
    val blockHash: String,
    val blockNumber: String,
    val era: String,
    val genesisHash: String,
    val method: String,
    val nonce: String,
    val specVersion: String,
    val tip: String,
    val transactionVersion: String,
    val signedExtensions: Array<String>,
    val version: Int,
)