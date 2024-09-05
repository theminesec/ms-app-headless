package com.minesec.msa.client.sdk.management.model_k

/**
 * @author eric.song
 * @since 2024/6/19 14:46
 */
data class DownloadKeyRequest_(

    val sdkId: String,

    val serverUrl: String,

    val customerId: String,

    val licenseId: String,

    val update: Boolean
)