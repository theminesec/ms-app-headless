package com.msa.headless.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minesec.msa.client.sdk.exception.ActivationCodeErrorException
import com.msa.headless.APP
import com.msa.headless.configs.ApplicationConfigStore
import com.msa.headless.configs.HeadlessConfigManager
import com.msa.headless.ui.TAG
import com.msa.headless.util.ImageUtils
import com.msa.headless.util.ParameterUtils
import com.theminesec.sdk.headless.SetupOptionBuilder
import com.theminesec.sdk.headless.model.WrappedResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @author eric.song
 * @since 2024/6/22 23:21
 */
class ActivationViewModel : ViewModel()  {
    private val _isShowLoading = MutableStateFlow(false)
    val isShowLoading: StateFlow<Boolean> = _isShowLoading
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage
    private val _activationSuccess = MutableStateFlow(false)
    val activationSuccess: StateFlow<Boolean> = _activationSuccess
    private val applicationConfigStore = ApplicationConfigStore()
    private val setupManager = HeadlessConfigManager()

    fun activation(activationCode: String,builderAction: (SetupOptionBuilder.() -> Unit)? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            _isShowLoading.value = true
            try {
                runCatching {
                    //1-activation
                    val activationResponse = setupManager.terminalActivation(activationCode)
                    if (!activationResponse.approved()) {
                        throw ActivationCodeErrorException(activationResponse.toErrorMessage())
                    }
                    //1.1-cache token and profileId
                    applicationConfigStore.setToken(activationResponse.data.token)
                    applicationConfigStore.setProfileId(activationResponse.data.profileId)
                    //2-get device information
                    val deviceInfoResponse = setupManager.getDeviceInfo(activationResponse.data.token)
                    if (!deviceInfoResponse.approved()) {
                        throw ActivationCodeErrorException(deviceInfoResponse.toErrorMessage())
                    }
                    //2.1-cache device info
                    ParameterUtils.setMerchantInfo(deviceInfoResponse)
                    //3-Setup the EMV AIDs, CAPKs, and terminal parameters, download payment keys
                    val setupResult = setupManager.initSetup(APP.instance.applicationContext, builderAction)
                    Log.d(TAG, "initSetup: $setupResult")
                    if (setupResult is WrappedResult.Failure) {
                        throw ActivationCodeErrorException("${setupResult.code}:${setupResult.message}")
                    }
                    //4-Try to download merchant logo
                    val logoUrl = ApplicationConfigStore().getDownloadMerchantLogoUrl()
                    if (logoUrl.isNotEmpty()) {
                        val downMerchantLogoResponse = setupManager.downloadMerchantLogo(logoUrl)
                        ImageUtils.saveLogo(downMerchantLogoResponse)
                    }
                }.onSuccess {
                    _activationSuccess.value = true
                }.onFailure { e ->
                    _errorMessage.value = e.message ?: "Unknown error"
                }
            } finally {
                _isShowLoading.value = false
            }
        }
    }
}