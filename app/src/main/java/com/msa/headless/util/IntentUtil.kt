package com.msa.headless.util

import android.os.Bundle
import android.util.Log

/**
 * @author eric.song
 * @since 2024/9/1 18:09
 */
object IntentUtil {
    private const val TAG = "IntentUtil"

    fun getStringExtra(bundle: Bundle?, key: String): String {
        return try {
            bundle?.getString(key) ?: ""
        } catch (e: Exception) {
            Log.e(TAG, "getStringExtra exception: ${e.message}")
            ""
        }
    }

    fun getIntExtra(bundle: Bundle?, key: String): Int {
        return try {
            bundle?.getInt(key, -1) ?: -1
        } catch (e: Exception) {
            Log.e(TAG, "getIntExtra exception: ${e.message}")
            -1
        }
    }

    fun getLongExtra(bundle: Bundle?, key: String): Long {
        return try {
            bundle?.getLong(key, -1L) ?: -1L
        } catch (e: Exception) {
            Log.e(TAG, "getLongExtra exception: ${e.message}")
            -1L
        }
    }

    fun getBooleanExtra(bundle: Bundle?, key: String): Boolean {
        return try {
            bundle?.getBoolean(key, true) ?: true
        } catch (e: Exception) {
            Log.e(TAG, "getBooleanExtra exception: ${e.message}")
            true
        }
    }

    fun getByteArrayExtra(bundle: Bundle?, key: String): ByteArray? {
        return try {
            bundle?.getByteArray(key)
        } catch (e: Exception) {
            Log.e(TAG, "getByteArrayExtra exception: ${e.message}")
            null
        }
    }
}