package com.minesec.msa.client.sdk.management.model_k

/**
 * @author eric.song
 * @since 2024/6/18 14:18
 */
open class ResultDto_(var code: Int, var msg: String) {
    fun approved(): Boolean {
        return code == 0
    }

    fun toErrorMessage(): String {
        return "$code: $msg"
    }

    fun getRespCode(): String {
        return "$code"
    }

    fun getRespText(): String {
        return msg
    }
}