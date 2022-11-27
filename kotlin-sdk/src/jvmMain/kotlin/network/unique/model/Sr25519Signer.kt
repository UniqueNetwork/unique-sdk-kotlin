package network.unique.model

import network.unique.signer.CryptoScheme
import network.unique.signer.Pair

class Sr25519Signer(private val seed: String, private val password: String?): Signer {

    override fun sign(data: String): String {
        val keyPair = Pair.fromSuri(CryptoScheme.Sr25519, seed, password)

        val signature = keyPair.sign(toByteArray(data.substring(2)))
            .joinToString("") { eachByte -> "%02x".format(eachByte) }
        return "0x01$signature"
    }

}