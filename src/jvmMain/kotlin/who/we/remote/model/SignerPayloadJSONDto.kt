package who.we.remote.model

import kotlinx.serialization.Serializable

@Serializable
class SignerPayloadJSONDto(
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