package com.msa.headless.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msa.headless.APP
import com.msa.headless.R
import com.msa.headless.configs.ApplicationConfigStore
import com.msa.headless.configs.HeadlessConfigManager
import com.msa.headless.model.StartingNavigator
import com.msa.headless.ui.TAG
import com.msa.headless.util.ImageUtils
import com.theminesec.sdk.headless.HeadlessSetup
import com.theminesec.sdk.headless.SetupOptionBuilder
import com.theminesec.sdk.headless.model.WrappedResult
import com.theminesec.sdk.headless.model.setup.SdkInitResp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.security.GeneralSecurityException

/**
 * @author eric.song
 * @since 2024/6/16 23:28
 */
class StartingViewModel : ViewModel() {
    private val setupManager = HeadlessConfigManager()
    private val _startingNavigatorEvent = MutableLiveData<StartingNavigator>()
    val startingNavigatorEvent: LiveData<StartingNavigator> get() = _startingNavigatorEvent
    val sdkMpocInitRespLiveData = MutableLiveData<WrappedResult<SdkInitResp>>()

    fun initHeadless(context: Application) = viewModelScope.launch {
        val clientResp =
//            HeadlessSetup.initSoftPos(context, "MineSec-lic_01J715Z467DQ5AYN1HFR6W8ZNT-20240905_132848.license") //For BCTC
            HeadlessSetup.initSoftPos(context, "Neurogine-lic_01J716014VR2XXWBT78HJRQY3J-20240909_081341.license") //For Neurogine
        sdkMpocInitRespLiveData.postValue(clientResp)
    }

    fun initSettings(context: Context, builderAction: (SetupOptionBuilder.() -> Unit)? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = runCatching {
                // Get device info from TMS
                val deviceInfoResult = setupManager.getDeviceInfo()
                Log.d(TAG, "code:" + deviceInfoResult.code)
                Log.d(TAG, "msg:" + deviceInfoResult.msg)
                Log.d(TAG, "getDeviceInfo: $deviceInfoResult")
                if (!deviceInfoResult.approved()) {
                    _startingNavigatorEvent.postValue(StartingNavigator.ToActivation)
                    return@runCatching
                }

                // Setup the EMV AIDs, CAPKs, and terminal parameters, download payment keys
                val setupResult = setupManager.initSetup(context, builderAction)
                Log.d(TAG, "initSetup: $setupResult")
                if (setupResult is WrappedResult.Failure) {
                    _startingNavigatorEvent.postValue(StartingNavigator.ToError("${setupResult.code}:${setupResult.message}"))
                    return@runCatching
                }

                // Try to download merchant logo
                val logoUrl = ApplicationConfigStore().getDownloadMerchantLogoUrl()
                if (logoUrl.isNotEmpty()) {
                    val downMerchantLogoResponse = setupManager.downloadMerchantLogo(logoUrl)
                    ImageUtils.saveLogo(downMerchantLogoResponse)
                }
                Log.d(TAG, "to Payment:")
                _startingNavigatorEvent.postValue(StartingNavigator.ToPayment)
            }
//
//            result.onSuccess {
//                Log.d(TAG, "to Payment:")
//                _startingNavigatorEvent.postValue(StartingNavigator.ToPayment)
//            }

            result.onFailure { e ->
                when (e) {
                    is IOException, is GeneralSecurityException -> {
                        _startingNavigatorEvent.postValue(StartingNavigator.ToError(APP.instance.getString(R.string.network_error_message)))
                    }

                    else -> {
                        _startingNavigatorEvent.postValue(StartingNavigator.ToError(APP.instance.getString(R.string.system_error_message)))
                    }
                }
            }
        }
    }

//    private suspend fun initSetup(context: Context, builderAction: (SetupOptionBuilder.() -> Unit)? = null): WrappedResult<SetupResp> {
//        return try {
//            val response = HeadlessSetup.initialSetup(context, builderAction)
//            WrappedResult.Success(response)
//        } catch (e: Exception) {
//            WrappedResult.Failure(e)
//        }
//    }
//
//    private suspend fun getDeviceInfo(): DeviceInfoResponseDto_ {
//        val url = ApplicationConfigStore().getTmsHostUrl()
//        return TMSClient_(url).getDeviceInfo(
//            token = ApplicationConfigStore().getToken(),
//            appVersion = APP.instance.getAppVersion(),
//            logoFileName = ApplicationConfigStore().getMerchantLogoFileName()
//        )
//    }
//
//    private suspend fun downloadMerchantLogo(downloadMerchantLogoUrl: String): DownloadMerchantLogoResponseDto_ {
//        val url = ApplicationConfigStore().getTmsHostUrl()
//        return TMSClient_(url).downloadMchLogo(
//            imgUrl = downloadMerchantLogoUrl
//        )
//    }
}