package com.msa.headless.errorcode

/**
 * @author eric.song
 * @since 2024/6/25 10:28
 */
enum class ServiceErrorActionLevel {
    INFO_ONLY,
    ACTION_RETRY_LEVEL,
    ACTION_RETRY_WITH_PIN_LEVEL,
    REACTIVE_LEVEL,
    RESET_LEVEL
}