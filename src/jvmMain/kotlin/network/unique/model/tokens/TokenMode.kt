package network.unique.model.tokens

import kotlinx.serialization.Serializable

@Serializable
enum class TokenMode {
    Nft,
    Fungible,
    ReFungible
}