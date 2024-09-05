package com.msa.headless

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.msa.headless.ui.TAG
import com.theminesec.sdk.headless.HeadlessSetup
import com.theminesec.sdk.headless.model.WrappedResult
import com.theminesec.sdk.headless.model.setup.SdkInitResp
import com.theminesec.sdk.headless.model.setup.SetupResp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author eric.song
 * @since 2024/6/16 23:01
 */
class APP : Application() {
    private val appScope = CoroutineScope(Dispatchers.Main)
    val sdkInitRespLiveData = MutableLiveData<WrappedResult<SdkInitResp>>()

    companion object {
        lateinit var instance: APP
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appScope.launch {
            Log.d(TAG,"start to call HeadlessSetup.initSoftPos.")
            val clientAppInitRes = HeadlessSetup.initSoftPos(this@APP, "2c2p-lic_01HYDRKKC850NA94SC00T62V3C-20240521_142410.license")
            sdkInitRespLiveData.postValue(clientAppInitRes)
        }
    }

    fun getAppVersion(): String {
        return try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            packageInfo.versionName
        } catch (e: Exception) {
            e.message ?: "Unknown error"
        }
    }
}