package com.minesec.msa.client.sdk.management.tms_

import com.minesec.msa.client.sdk.Client
import com.minesec.msa.client.sdk.ClientOpts
import com.minesec.msa.client.sdk.management.model_k.ActivationRequest_
import com.minesec.msa.client.sdk.management.model_k.ActivationResponseDto_
import com.minesec.msa.client.sdk.management.model_k.DeviceInfoResponseDto_
import com.minesec.msa.client.sdk.management.model_k.DownloadEMVParamRequest_
import com.minesec.msa.client.sdk.management.model_k.DownloadKeyRequest_
import com.minesec.msa.client.sdk.management.model_k.DownloadKeyResponse_
import com.minesec.msa.client.sdk.management.model_k.DownloadMerchantLogoResponseDto_
import com.minesec.msa.client.sdk.management.model_k.DownloadParamResponseDto_
import com.minesec.msa.client.sdk.management.model_k.GetDeviceInfoRequest_
import com.minesec.msa.client.sdk.management.model_k.ResultDto_
import com.minesec.msa.client.sdk.management.tms.TMSApiService

/**
 * @author eric.song
 * @since 2024/6/18 14:32
 */
class TMSClient_(private val url: String) : Client() {
    private val service: TMSApiService by lazy {
        startService(
            TMSApiService::class.java,
            url,
            ClientOpts.DEFAULT_CONNECT_TIMEOUT.toLong(),
            ClientOpts.DEFAULT_READ_TIMEOUT.toLong()
        )
    }

    suspend fun getDeviceInfo(token: String, appVersion: String, logoFileName: String): DeviceInfoResponseDto_ {
        val headers = buildHeaders(token)
        return service.getDeviceInfo(headers, GetDeviceInfoRequest_(appVersion, logoFileName))
    }

    suspend fun downloadPaymentKeys(token: String, sdkId: String, serverUrl: String,customerId: String,licenseId: String,update: Boolean): DownloadKeyResponse_ {
        val headers = buildHeaders(token)
        return service.downloadPaymentKeys(headers, DownloadKeyRequest_(sdkId,serverUrl,customerId,licenseId,update))
    }

    suspend fun downloadEMVParam(token: String, sdkId: String): DownloadParamResponseDto_ {
        val headers = buildHeaders(token)
        return service.downloadEMVParam(headers, DownloadEMVParamRequest_(sdkId))
    }

    suspend fun downloadMchLogo(imgUrl: String): DownloadMerchantLogoResponseDto_ {
        return DownloadMerchantLogoResponseDto_(service.downloadMchLogo(imgUrl))
    }

    suspend fun activation(activationCode: String, sdkId: String, deviceName: String): ActivationResponseDto_ {
        return service.activation(ActivationRequest_(activationCode,sdkId,deviceName))
    }

    suspend fun logout(token: String): ResultDto_ {
        return service.logout(buildHeaders(token))
    }

    private fun buildHeaders(token: String): Map<String, String> {
        val headers = HashMap<String, String>()
        headers["token"] = token
        return headers
    }
}