package com.minesec.msa.client.sdk.management.model_k

import java.io.Serializable

/**
 * @author eric.song
 * @since 2024/6/19 15:19
 */
data class DownloadParamDto_(
    val emvParams: DownloadEMVParamDto_,
    val emvAID: List<DownloadEMVAidDto_>
): Serializable
