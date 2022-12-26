package network.unique.model

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import network.unique.signer.CryptoScheme
import network.unique.signer.Pair

class Sr25519SignerWrapper(seed: String, password: String?, generate: Boolean) : SignerWrapper {

    private var pair: Pair

    init {
        pair = if (generate) {
            val pairStr = Pair.generate(CryptoScheme.Sr25519, password)
            val moshi: Moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<NativePairWrapper> = moshi.adapter(NativePairWrapper::class.java)

            val pairInfo: NativePairWrapper = jsonAdapter.fromJson(pairStr)!!

            Pair.fromSuri(CryptoScheme.Sr25519, pairInfo.secretSeed, password)
        } else {
            Pair.fromSuri(CryptoScheme.Sr25519, seed, password)
        }
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