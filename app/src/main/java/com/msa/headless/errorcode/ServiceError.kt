package com.msa.headless.errorcode

/**
 * @author eric.song
 * @since 2024/6/25 10:33
 */
data class ServiceError(
    val serviceErrorCode: ServiceErrorCode,
    val errorDescription: String,
    val detailCode: Int,
    val detailMessage: String = ""
)

fun Int.getErrorCode(): ServiceError {
    return when (this) {
        11000,
        11001,
        11002 -> ServiceError(ServiceErrorCode.DEVICE_INVALID_AUTH, "Invalid session-token", this)
        10003,
        11003,
        12001,
        12002,
        12012 -> ServiceError(ServiceErrorCode.DEVICE_INVALID_SESSION, "Device is log-out. Please re-activate", this)
        11004 -> ServiceError(ServiceErrorCode.DEVICE_STATUS_SUSPENDED, "Device is suspended. Please contact admin", this)
        11005 -> ServiceError(
            ServiceErrorCode.DEVICE_INVALID_ACTIVATION_CODE,
            "Activation code invalid. Please check and retry.",
            this
        )
        //11006 -> ServiceError()
        11007 -> ServiceError(ServiceErrorCode.DEVICE_INVALID_ACTIVATION_CODE, "Activation code is expired.", this)
        11008,
        11021,
        12013,
        12014,
        12015,
        12017,
        12021,
        12024,
        12211 -> ServiceError(
            ServiceErrorCode.SYSTEM_INVALID_SETTING,
            "Incomplete merchant configuration. Please contact admin",
            this
        )
        11009 -> ServiceError(ServiceErrorCode.SYSTEM_SIGN_ON_FAILS, "System sign-on fails. Please retry later.", this)
        11020,
        12003,
        12016,
        11026,
        11027 -> ServiceError(
            ServiceErrorCode.SYSTEM_CRYPTO_ERROR,
            "System crypto fails. Please re-activate application",
            this
        )
        11022,
        11025 -> ServiceError(
            ServiceErrorCode.AMS_CONNECTION_FAILS,
            "System internal AMS connection fails. Please retry later.",
            this
        )
        // need attestation
        11023 -> ServiceError(
            ServiceErrorCode.AMS_DEVICE_VALIDATION_FAILS,
            "Device status validation fails. Please retry later",
            this
        )
        // re-registration!
        11024 -> ServiceError(
            ServiceErrorCode.DEVICE_NOT_EXIST,
            "Device identity validation fails. Please re-factory application",
            this
        )
        11030 -> ServiceError(ServiceErrorCode.USER_INVALID_ACCESS_TOKEN, "Invalid access token. Please check and retry", this)
        11031,
        11034 -> ServiceError(ServiceErrorCode.USER_INVALID_INPUT, "Invalid email. Please check and retry", this)
        11032,
        12100,
        12101,
        12200 -> ServiceError(ServiceErrorCode.ORDER_NOT_EXIST, "Order does not exist", this)
        11033 -> ServiceError(ServiceErrorCode.ORDER_NOT_APPROVED, "Order not approved", this)
        12000 -> ServiceError(ServiceErrorCode.DEVICE_INVALID_SIGN, "Device sign verification fails", this)
        12010 -> ServiceError(ServiceErrorCode.ORDER_CREATION_FAILS, "Order creation fails. Please retry later", this)
        12018,
            // 待查!!
        12032 -> ServiceError(
            ServiceErrorCode.TXN_HOST_SYSTEM_ERROR,
            "Transaction host system error. Please retry later",
            this
        )
        12019,
        12020,
        12214,
        12215,
        12222,
        12216 -> ServiceError(ServiceErrorCode.ORDER_UPDATE_FAILS, "Order update fails.", this)
        12022 -> ServiceError(ServiceErrorCode.USER_INVALID_INPUT, "Authentication fails. Please check and retry", this)
        12023 -> ServiceError(ServiceErrorCode.SYSTEM_UNDER_SETTLEMENT, "System under settlement. Please retry later.", this)
        12030,
        12220 -> ServiceError(
            ServiceErrorCode.TXN_HOST_CONNECTION_FAILS,
            "Transaction host connection fails. Please retry later",
            this
        )
        12031,
        12221 -> ServiceError(ServiceErrorCode.TXN_HOST_DECLINE, "Transaction host declines", this)
        12033 -> ServiceError(ServiceErrorCode.TXN_HOST_REQUIRE_PIN, "Transaction requires PIN, please do PIN entry", this)
        12102,
        12103,
        12201,
        12202,
        12203,
        12204,
        12205,
        12206,
        12207 -> ServiceError(ServiceErrorCode.ORDER_ACTION_NOT_ALLOWED, "Action not allowed", this)
        //待查！！ 到底是什么原因？host？ system？
        12213 -> ServiceError(ServiceErrorCode.SYSTEM_INTERNAL_ERROR, "System internal error.", this)
        12300 -> ServiceError(ServiceErrorCode.SYSTEM_RECONCILIATION_FAILS, "System reconciliation fails", this)
        else -> ServiceError(ServiceErrorCode.UNKNOWN_ERROR_CODE, "System internal error.", this)
    }
}

