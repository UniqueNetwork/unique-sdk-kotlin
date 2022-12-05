package network.unique.sdk.android.key

import android.content.Context

interface PasswordStorageInterface {

    fun init(context: Context?): Boolean

    fun setData(key: String?, data: ByteArray?)

    fun getData(key: String?): ByteArray?

    fun remove(key: String?)

}