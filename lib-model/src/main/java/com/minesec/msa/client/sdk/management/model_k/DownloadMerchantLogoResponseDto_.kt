package com.minesec.msa.client.sdk.management.model_k

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.BufferedSource

/**
 * @author eric.song
 * @since 2024/6/19 15:28
 */
class DownloadMerchantLogoResponseDto_(
    val responseBody: ResponseBody
) : ResponseBody() {

    override fun contentType(): MediaType? {
        return responseBody.contentType()
    }

    override fun contentLength(): Long {
        return responseBody.contentLength()
    }

    override fun source(): BufferedSource {
        return responseBody.source()
    }
}