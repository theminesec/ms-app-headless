package com.minesec.msa.client.sdk.management.model_k


/**
 * @author eric.song
 * @since 2024/6/19 14:51
 */
data class DownloadKeyResponse_(val data: SessionKeyDto_, val success: Int, val message: String): ResultDto_(success, message)
