package com.minesec.msa.client.sdk.management.model_k


/**
 * @author eric.song
 * @since 2024/6/22 23:28
 */
data class ActivationResponseDto_(val data: ActivationData_, val success: Int, val message: String): ResultDto_(success, message)
