package com.msa.headless.model

/**
 * @author eric.song
 * @since 2024/9/1 21:01
 */
interface SettingsNavigator : Navigator {
    fun getApplicationVersion(): String

    fun showLoadingProgress(resId: Int)

    fun hideLoadingProgress()

    fun displayError(resId: Int, errorMessage: String)

    fun onLogoutSuccess()
}