package com.msa.headless.util

import com.minesec.msa.client.sdk.management.model_k.DeviceInfoResponseDto_
import com.msa.headless.configs.ApplicationConfigStore
import com.msa.headless.configs.PaymentMethodStore

/**
 * @author eric.song
 * @since 2024/7/11 16:04
 */
object ParameterUtils {

    fun setMerchantInfo(deviceInfoResponseDto: DeviceInfoResponseDto_) {
        val applicationConfigStore = ApplicationConfigStore()
        deviceInfoResponseDto.data.let { data ->
            applicationConfigStore.setMerchantName(data.mchName)
            applicationConfigStore.setMerchantUUID(data.uuid)
            applicationConfigStore.setBackendSoftwareVersion(data.sysVersion)
            applicationConfigStore.setHelpSupportUrl(data.helpSupportUrl)
            applicationConfigStore.setCurrencyText(data.currency)

            val downloadMerchantLogoUrl = data.mchLogo
            if (!downloadMerchantLogoUrl.isNullOrEmpty()) {
                applicationConfigStore.setDownloadMerchantLogoUrl(downloadMerchantLogoUrl)
                applicationConfigStore.setMerchantLogoFileName(ImageUtils.getLogoFileNameFromUrl(downloadMerchantLogoUrl))
            }

            if (!data.mchContactAddress.isNullOrEmpty()) {
                applicationConfigStore.setMerchantAddress1(data.mchContactAddress)
            }
            applicationConfigStore.setSupportDownloadConfigBeforeTransFlag(data.loadEmvConfig)
            applicationConfigStore.setEnableExternalMPOSCardReader(data.enableExternalReader)
            // Install the supported QR payment methods
            val supportedMPQRMethods = data.supportedMPQRMethods
            if (!supportedMPQRMethods.isNullOrEmpty()) {
                val paymentMethodStore = PaymentMethodStore()
                supportedMPQRMethods.forEach { method ->
                    paymentMethodStore.save("QR_$method", method)
                }
            }
        }
    }
}