package network.unique.sdk.android

import android.content.Context

class PasswordEncryptImpl(context: Context) {

    private val passwordStorage: PasswordStorageHelper = PasswordStorageHelper(context)

    fun savePassword(password: String) {
        passwordStorage.setData(PASSWORD_KEY, password.toByteArray())
    }

    fun getPassword(): String {
        return String((passwordStorage.getData(PASSWORD_KEY) ?: ByteArray(0)))
    }

    fun removePassword() {
        passwordStorage.remove(PASSWORD_KEY)
    }

    companion object {
        private const val PASSWORD_KEY = "password"
    }

}