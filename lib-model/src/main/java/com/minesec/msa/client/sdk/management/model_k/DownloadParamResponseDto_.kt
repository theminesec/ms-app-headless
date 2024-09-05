package com.minesec.msa.client.sdk.management.model_k

import java.io.Serializable

/**
 * @author eric.song
 * @since 2024/6/19 15:21
 */
data class DownloadParamResponseDto_(val data: DownloadParamDto_, val success: Int, val message: String): ResultDto_(success, message),Serializable
