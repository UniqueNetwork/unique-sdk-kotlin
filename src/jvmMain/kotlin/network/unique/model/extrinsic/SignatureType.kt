package network.unique.model.extrinsic

import kotlinx.serialization.Serializable

@Serializable
enum class SignatureType {
    sr25519,
    ed25519,
    ecdsa,
    ethereum
}