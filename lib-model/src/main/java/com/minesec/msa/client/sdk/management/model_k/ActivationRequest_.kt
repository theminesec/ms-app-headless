package com.minesec.msa.client.sdk.management.model_k

/**
 * @author eric.song
 * @since 2024/6/22 23:31
 */
data class ActivationRequest_(
    val activationCode: String,
    val sdkId: String,
    val deviceName: String
)