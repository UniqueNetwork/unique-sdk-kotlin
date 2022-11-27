package network.unique.model

interface SignerWrapper {

    fun sign(data: String): String

    fun toByteArray(data: String): ByteArray {
        return data.chunked(2)
            .map { it.toInt(16).toByte() }
            .toByteArray()
    }

}