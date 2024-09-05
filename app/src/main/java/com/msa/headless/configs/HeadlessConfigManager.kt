package com.msa.headless.configs

import android.content.Context
import android.os.Build
import com.minesec.msa.client.sdk.management.model_k.ActivationResponseDto_
import com.minesec.msa.client.sdk.management.model_k.DeviceInfoResponseDto_
import com.minesec.msa.client.sdk.management.model_k.DownloadMerchantLogoResponseDto_
import com.minesec.msa.client.sdk.management.tms_.TMSClient_
import com.msa.headless.APP
import com.theminesec.sdk.headless.HeadlessSetup
import com.theminesec.sdk.headless.SetupOptionBuilder
import com.theminesec.sdk.headless.model.WrappedResult
import com.theminesec.sdk.headless.model.setup.SetupResp

/**
 * @author eric.song
 * @since 2024/8/5 13:51
 */
class HeadlessConfigManager {

    suspend fun initSetup(context: Context, builderAction: (SetupOptionBuilder.() -> Unit)? = null): WrappedResult<SetupResp> {
        return try {
            val response = HeadlessSetup.initialSetup(context, builderAction)
            WrappedResult.Success(response)
        } catch (e: Exception) {
            WrappedResult.Failure(e)
        }
    }

    suspend fun terminalActivation(activationCode: String): ActivationResponseDto_ {
        val url = ApplicationConfigStore().getTmsHostUrl()
        val sdkId = ApplicationConfigStore().getSdkId()
        return TMSClient_(url).activation(activationCode, sdkId, Build.MODEL)
    }

    suspend fun getDeviceInfo(): DeviceInfoResponseDto_ {
        val url = ApplicationConfigStore().getTmsHostUrl()
        return TMSClient_(url).getDeviceInfo(
            token = ApplicationConfigStore().getToken(),
            appVersion = APP.instance.getAppVersion(),
            logoFileName = ApplicationConfigStore().getMerchantLogoFileName()
        )
    }

    suspend fun getDeviceInfo(token: String): DeviceInfoResponseDto_ {
        val url = ApplicationConfigStore().getTmsHostUrl()
        return TMSClient_(url).getDeviceInfo(
            token = token,
            appVersion = APP.instance.getAppVersion(),
            logoFileName = ApplicationConfigStore().getMerchantLogoFileName()
        )
    }

    suspend fun downloadMerchantLogo(downloadMerchantLogoUrl: String): DownloadMerchantLogoResponseDto_ {
        val url = ApplicationConfigStore().getTmsHostUrl()
        return TMSClient_(url).downloadMchLogo(
            imgUrl = downloadMerchantLogoUrl
        )
    }
}