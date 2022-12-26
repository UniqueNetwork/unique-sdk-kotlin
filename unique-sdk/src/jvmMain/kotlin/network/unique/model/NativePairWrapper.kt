package network.unique.model

import com.squareup.moshi.Json

class NativePairWrapper (

    @Json(name = "nonce")
    val accountId: String,
    @Json(name = "networkId")
    val networkId: String,
    @Json(name = "publicKey")
    val publicKey: String,
    @Json(name = "secretPhrase")
    val secretPhrase: String,
    @Json(name = "secretSeed")
    val secretSeed: String,
    @Json(name = "ss58Address")
    val ss58Address: String,
    @Json(name = "ss58PublicKey")
    val ss58PublicKey: String,

)