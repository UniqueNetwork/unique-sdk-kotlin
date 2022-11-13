package network.unique.sdk.android

import android.content.Context
import android.util.Log
import network.unique.sdk.android.Constants.Companion.tag
import network.unique.sdk.android.key.PasswordStorageHelperSDK16
import network.unique.sdk.android.key.PasswordStorageHelperSDK18
import network.unique.sdk.android.key.PasswordStorageInterface

class PasswordStorageHelper(context: Context) {

    private var passwordStorage: PasswordStorageInterface?

    init {
        passwordStorage = PasswordStorageHelperSDK18();

        var isInitialized: Boolean? = false;

        try {
            isInitialized = passwordStorage?.init(context);
        } catch (ex: Exception) {
            Log.e(tag, "PasswordStorage initialisation error:" + ex.message, ex);
        }

        if (isInitialized != true && passwordStorage is PasswordStorageHelperSDK18) {
            passwordStorage = PasswordStorageHelperSDK16();
            passwordStorage?.init(context);
        }
    }


    fun setData(key: String?, data: ByteArray?) {
        passwordStorage?.setData(key!!, data ?: ByteArray(0))
    }

    fun getData(key: String?): ByteArray? {
        return passwordStorage?.getData(key ?: "")
    }

    fun remove(key: String?) {
        passwordStorage?.remove(key ?: "")
    }

}