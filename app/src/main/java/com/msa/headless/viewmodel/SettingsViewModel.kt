package com.msa.headless.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minesec.msa.client.sdk.management.tms_.TMSClient_
import com.msa.headless.R
import com.msa.headless.configs.ApplicationConfigStore
import com.msa.headless.model.SettingsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author eric.song
 * @since 2024/9/1 20:54
 */
class SettingsViewModel : ViewModel() {
    private lateinit var navigator: SettingsNavigator

    fun onActivityCreated(navigator: SettingsNavigator) {
        this.navigator = navigator
    }

    fun logout() {
        viewModelScope.launch {
            val configStore = ApplicationConfigStore()
            val tmsClient = TMSClient_(configStore.getTmsHostUrl())
            navigator.showLoadingProgress(R.string.display_log_out)
            try {
                withContext(Dispatchers.IO) {
                    tmsClient.logout(configStore.getToken())
                }
            } catch (tr: Exception) {
                navigator.hideLoadingProgress()
                tr.message?.let { navigator.displayError(R.string.log_out_failed, it) }
                return@launch
            }
            navigator.onLogoutSuccess()
        }
    }

    fun getFormatApplicationVersion(): String {
        return navigator.getApplicationVersion()
    }
}