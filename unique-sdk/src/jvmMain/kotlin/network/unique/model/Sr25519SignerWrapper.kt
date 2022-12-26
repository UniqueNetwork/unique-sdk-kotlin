package network.unique.model

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import network.unique.signer.CryptoScheme
import network.unique.signer.Pair

class Sr25519SignerWrapper : SignerWrapper {

    private var pair: Pair

    constructor(entry: String?, generate: Boolean) {
        pair = if (generate) {
            val pairStr = Pair.generate(CryptoScheme.Sr25519, entry)
            val moshi: Moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<NativePairWrapper> = moshi.adapter(NativePairWrapper::class.java)

            val pairInfo: NativePairWrapper = jsonAdapter.fromJson(pairStr)!!

            Pair.fromSuri(CryptoScheme.Sr25519, pairInfo.secretSeed, entry)
        } else {
            Pair.fromSuri(CryptoScheme.Sr25519, entry, null)
        }
    }

    constructor(seed: String, password: String) {
        pair = Pair.fromSuri(CryptoScheme.Sr25519, seed, password)
    }

    override fun sign(data: String): String {
        val signature = pair.sign(toByteArray(data.substring(2)))
            .joinToString("") { eachByte -> "%02x".format(eachByte) }
        return "0x01$signature"
    }

    override fun close() {
        pair.close()
    }

}