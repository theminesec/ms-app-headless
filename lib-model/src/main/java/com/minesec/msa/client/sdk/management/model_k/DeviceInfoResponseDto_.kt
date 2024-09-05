package com.minesec.msa.client.sdk.management.model_k
/**
 * @author eric.song
 * @since 2024/6/18 14:13
 */
data class DeviceInfoResponseDto_(val data: DeviceInfoData_, val success: Int, val message: String): ResultDto_(success, message)
