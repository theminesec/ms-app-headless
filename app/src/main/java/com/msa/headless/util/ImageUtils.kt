package com.msa.headless.util

import android.util.Base64
import com.minesec.msa.client.sdk.management.model_k.DownloadMerchantLogoResponseDto_
import com.msa.headless.configs.ApplicationConfigStore



/**
 * @author eric.song
 * @since 2024/7/11 16:06
 */
object ImageUtils {
    fun getLogoFileNameFromUrl(url: String?): String {
        var fileName = ""

        if (url.isNullOrEmpty()) {
            return fileName
        }

        val parts = url.split("/")
        if (parts.isNotEmpty()) {
            fileName = parts.last()
            if (!fileName.lowercase().endsWith(".png")) {
                fileName = ""
            }
        }

        return fileName
    }

    fun saveLogo(downloadMerchantLogoResponseDto: DownloadMerchantLogoResponseDto_?) {
        val responseBody = downloadMerchantLogoResponseDto?.responseBody?: return
        try {
            val byteArray = responseBody.bytes()
            val encodedString = Base64.encodeToString(byteArray, Base64.NO_WRAP)
            ApplicationConfigStore().setMerchantLogoData(encodedString)
        } catch (e: Exception) {
            //TODO
            e.printStackTrace()
        }
    }

}