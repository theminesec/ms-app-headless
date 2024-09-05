package com.msa.headless.errorcode

/**
 * @author eric.song
 * @since 2024/6/25 10:31
 */
enum class ServiceErrorCode(val code: Int, val info: String, val actionLevel: ServiceErrorActionLevel = ServiceErrorActionLevel.INFO_ONLY) {
    DEVICE_NOT_EXIST(10, "Device is not identified.", ServiceErrorActionLevel.RESET_LEVEL),
    DEVICE_INVALID_AUTH(11, "Authentication fails.", ServiceErrorActionLevel.REACTIVE_LEVEL),
    DEVICE_INVALID_SESSION(12, "Session is expired.", ServiceErrorActionLevel.REACTIVE_LEVEL),
    DEVICE_INVALID_SIGN(13, "Signature verification fails.", ServiceErrorActionLevel.REACTIVE_LEVEL),
    DEVICE_INVALID_ACTIVATION_CODE(14, "Activation code is invalid. Please check and retry.",
        ServiceErrorActionLevel.INFO_ONLY
    ),
    DEVICE_STATUS_SUSPENDED(15, "device is suspended.", ServiceErrorActionLevel.REACTIVE_LEVEL),
    AMS_CONNECTION_FAILS(21, "System internal AMS connection fails.",
        ServiceErrorActionLevel.ACTION_RETRY_LEVEL
    ),
    AMS_DEVICE_VALIDATION_FAILS(23, "Device status not allowed.",
        ServiceErrorActionLevel.ACTION_RETRY_LEVEL
    ),
    USER_INVALID_ACCESS_TOKEN(31, "access token is invalid", ServiceErrorActionLevel.INFO_ONLY),
    USER_INVALID_INPUT(32, "input data is invalid", ServiceErrorActionLevel.INFO_ONLY),
    SYSTEM_INVALID_SETTING(41, "System setting is incomplete or incorrect, please contact admin",
        ServiceErrorActionLevel.INFO_ONLY
    ),
    SYSTEM_SIGN_ON_FAILS(42, "System sign on fails, please retry later",
        ServiceErrorActionLevel.ACTION_RETRY_LEVEL
    ),
    SYSTEM_CRYPTO_ERROR(43, "System decryption fails. Please re-activate device.",
        ServiceErrorActionLevel.REACTIVE_LEVEL
    ),
    SYSTEM_UNDER_SETTLEMENT(45, "System is under settlement.",
        ServiceErrorActionLevel.REACTIVE_LEVEL
    ),
    SYSTEM_RECONCILIATION_FAILS(46, "System reconciliation fails.",
        ServiceErrorActionLevel.INFO_ONLY
    ),
    SYSTEM_INTERNAL_ERROR(47, "System internal errors", ServiceErrorActionLevel.INFO_ONLY),
    ORDER_NOT_EXIST(71, "Order does not exist.", ServiceErrorActionLevel.INFO_ONLY),
    ORDER_CREATION_FAILS(72, "Order creation fails. Please retry later.",
        ServiceErrorActionLevel.ACTION_RETRY_LEVEL
    ),
    ORDER_UPDATE_FAILS(73, "Order update fails. Please retry later.",
        ServiceErrorActionLevel.ACTION_RETRY_LEVEL
    ),
    ORDER_NOT_APPROVED(74, "Order is not approved.", ServiceErrorActionLevel.INFO_ONLY),
    ORDER_ACTION_NOT_ALLOWED(75, "Action is not allowed.", ServiceErrorActionLevel.INFO_ONLY),
    TXN_HOST_CONNECTION_FAILS(90, "Host connection fails. Please retry later.",
        ServiceErrorActionLevel.ACTION_RETRY_LEVEL
    ),
    TXN_HOST_SYSTEM_ERROR(91, "Host system error.", ServiceErrorActionLevel.INFO_ONLY),
    TXN_HOST_DECLINE(92, "Host decline.", ServiceErrorActionLevel.INFO_ONLY),
    TXN_HOST_REQUIRE_PIN(93, "host required PIN entry.",
        ServiceErrorActionLevel.ACTION_RETRY_WITH_PIN_LEVEL
    ),
    UNKNOWN_ERROR_CODE(999, "error code is not identified", ServiceErrorActionLevel.INFO_ONLY)
}