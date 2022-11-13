package network.unique.sdk.android.key

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import network.unique.sdk.android.Constants.Companion.PREFS_NAME

class PasswordStorageHelperSDK16 : PasswordStorageInterface {

    private var preferences: SharedPreferences? = null

    override fun init(context: Context?): Boolean {
        preferences = context?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return true
    }

    override fun setData(key: String?, data: ByteArray?) {
        if (data == null) return
        val editor = preferences?.edit()
        editor?.putString(key, Base64.encodeToString(data, Base64.DEFAULT))
        editor?.apply()
    }

    override fun getData(key: String?): ByteArray? {
        val res = preferences?.getString(key, null) ?: return null
        return Base64.decode(res, Base64.DEFAULT)
    }

    override fun remove(key: String?) {
        val editor = preferences?.edit()
        editor?.remove(key)
        editor?.apply()
    }

}