package network.unique.sdk.android.key

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.KeyChain
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyInfo
import android.security.keystore.KeyProperties
import android.util.Base64
import android.util.Log
import network.unique.R
import network.unique.sdk.android.Constants.Companion.PREFS_NAME
import network.unique.sdk.android.Constants.Companion.tag
import java.io.IOException
import java.math.BigInteger
import java.security.*
import java.security.cert.CertificateException
import java.security.spec.AlgorithmParameterSpec
import java.security.spec.InvalidKeySpecException
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.security.auth.x500.X500Principal

class PasswordStorageHelperSDK18 : PasswordStorageInterface {

    companion object {

        private const val KEY_ALGORITHM_RSA: String = "RSA"
        private const val KEYSTORE_PROVIDER_ANDROID_KEYSTORE: String = "AndroidKeyStore"
        private const val RSA_ECB_PKCS1_PADDING: String = "RSA/ECB/PKCS1Padding"

    }

    private var preferences: SharedPreferences? = null
    private var alias: String? = null

    override fun init(context: Context?): Boolean {
        preferences = context?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        alias = context?.getString(R.string.app_package)

        val ks: KeyStore?

        try {
            ks = KeyStore.getInstance(KEYSTORE_PROVIDER_ANDROID_KEYSTORE)
            ks?.load(null)

            val privateKey: Key? = ks?.getKey(alias, null)

            if (privateKey != null && ks.getCertificate(alias) != null) {
                val publicKey: PublicKey? = ks.getCertificate(alias).publicKey

                if (publicKey != null) {
                    return true
                }
            }
        } catch (ex: Exception) {
            return false
        }

        val start = GregorianCalendar()
        val end = GregorianCalendar()
        end.add(Calendar.YEAR, 10)

        val spec: AlgorithmParameterSpec?
        if (Build.VERSION.SDK_INT < 23) {
            spec = context?.let {
                android.security.KeyPairGeneratorSpec.Builder(it)
                    .setAlias(alias ?: "")
                    .setSubject(X500Principal("CN=$alias"))
                    .setSerialNumber(BigInteger.valueOf(1337))
                    .setStartDate(start.time).setEndDate(end.time)
                    .build()
            }
        } else {
            spec = KeyGenParameterSpec.Builder(alias ?: "", KeyProperties.PURPOSE_DECRYPT)
                .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                .build()
        }

        val kpGenerator: KeyPairGenerator
        try {
            kpGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM_RSA, KEYSTORE_PROVIDER_ANDROID_KEYSTORE)
            kpGenerator.initialize(spec)
            kpGenerator.generateKeyPair()
        } catch (e: Exception) {
            when (e) {
                is NoSuchAlgorithmException, is InvalidAlgorithmParameterException, is NoSuchProviderException -> {
                    try {
                        ks?.deleteEntry(alias)
                    } catch (_: Exception) {
                    }
                }
            }

        }

        try {

            val isHardwareBackedKeystoreSupported: Boolean = if (Build.VERSION.SDK_INT < 23) {
                KeyChain.isBoundKeyAlgorithm(KeyProperties.KEY_ALGORITHM_RSA)
            } else {
                val privateKey: Key = ks.getKey(alias, null)
                val keyFactory: KeyFactory = KeyFactory.getInstance(privateKey.algorithm, "AndroidKeyStore")
                val keyInfo: KeyInfo = keyFactory.getKeySpec(privateKey, KeyInfo::class.java)
                keyInfo.isInsideSecureHardware
            }
            Log.d(tag, "Hardware-Backed Keystore Supported: $isHardwareBackedKeystoreSupported")
        } catch (_: Exception) {
        }

        return true
    }

    override fun setData(key: String?, data: ByteArray?) {
        var ks: KeyStore? = null
        try {
            ks = KeyStore.getInstance(KEYSTORE_PROVIDER_ANDROID_KEYSTORE)

            ks.load(null)
            if (ks.getCertificate(alias) == null) return

            val publicKey: PublicKey? = ks.getCertificate(alias).publicKey

            if (publicKey == null) {
                Log.d(tag, "Error: Public key was not found in Keystore")
                return
            }

            val value: String = encrypt(publicKey, data)

            val editor: SharedPreferences.Editor? = preferences?.edit()
            editor?.putString(key, value)
            editor?.apply()
        } catch (e: Exception) {
            when (e) {
                is NoSuchAlgorithmException, is InvalidKeyException, is NoSuchPaddingException,
                is IllegalBlockSizeException, is BadPaddingException, is NoSuchProviderException,
                is InvalidKeySpecException, is KeyStoreException, is CertificateException, is IOException -> {

                    try {
                        ks?.deleteEntry(alias)
                    } catch (_: Exception) {
                    }
                }
            }
        }
    }


    override fun getData(key: String?): ByteArray? {
        var ks: KeyStore? = null
        try {
            ks = KeyStore.getInstance(KEYSTORE_PROVIDER_ANDROID_KEYSTORE)
            ks.load(null)
            val privateKey: Key = ks.getKey(alias, null)
            return decrypt(privateKey, preferences?.getString(key, null))
        } catch (e: Exception) {
            try {
                ks?.deleteEntry(alias)
            } catch (_: Exception) {
            }
        }
        return null
    }


    override fun remove(key: String?) {
        val editor: SharedPreferences.Editor? = preferences?.edit()
        editor?.remove(key)
        editor?.apply()
    }

    private fun encrypt(encryptionKey: PublicKey, data: ByteArray?): String {
        val cipher: Cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING)
        cipher.init(Cipher.ENCRYPT_MODE, encryptionKey)
        val encrypted: ByteArray = cipher.doFinal(data)
        return Base64.encodeToString(encrypted, Base64.DEFAULT)
    }

    private fun decrypt(decryptionKey: Key, encryptedData: String?): ByteArray? {
        if (encryptedData == null) return null
        val encryptedBuffer: ByteArray = Base64.decode(encryptedData, Base64.DEFAULT)
        val cipher: Cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING)
        cipher.init(Cipher.DECRYPT_MODE, decryptionKey)
        return cipher.doFinal(encryptedBuffer)
    }

}