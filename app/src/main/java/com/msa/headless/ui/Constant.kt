package com.msa.headless.ui

const val TAG = "headless_msa"

const val SP_NAME = "soft_pos_app_configs"

const val MGMT_HOST_BASE_URL = "mgmt_host_url"
const val TMS_HOST_BASE_URL = "tms_host_url"
const val PAYMENT_HOST_BASE_URL = "pay_host_url"
const val PAYMENT_KEY = "pay_key"
const val MGMT_TOKEN = "mgmt_token"
const val MGMT_TOKEN_BACKUP = "mgmt_token_backup"
const val HEADLESS_PROFILE_ID = "headless_profile_id"
const val CONNECTION_TIMEOUT = "connect_timeout"
const val RECEIVE_TIMEOUT = "receive_timeout"
const val READ_CARD_TIMEOUT = "read_card_timeout"
const val TERMINAL_TYPE = "terminal_type"
const val TERMINAL_COUNTRY_CODE = "terminal_country_code"
const val TXN_CURRENCY_CODE = "txn_currency_code"
const val TERMINAL_CAPABILITY = "terminal_capability"
const val TTQ = "emv_cl_ttq"
const val AMEX_READER_CAPABILITY = "amex_reader_capability"
const val AMEX_READER_CAPABILITY_EX = "amex_reader_capability_ex"
const val AMEX_FLOOR_LIMIT = "amex_floor_limit"
const val EMV_FLAGS = "emv_flags"
const val MERCHANT_ID = "merchant_id"
const val MERCHANT_NAME = "merchant_name"
const val TERMINAL_ID = "terminal_id"
const val APPLICATION_ID = "app_id"
const val WAY_CODE = "way_code"
const val VERSION = "version"
const val SIGN_TYPE = "signature_type"
const val EXPIRED_TIME = "expired_time"
const val NOTIFY_URL = "notify_url"
const val RETURN_URL = "return_url"
const val TRANS_SUBJECT = "trans_subject"
const val DIVISION_MODE = "division_mode"
const val SDK_ID = "sdk_id"
const val SDK_LICENSE_ID = "sdk_license_id"
const val SDK_CUSTOMER_ID = "sdk_customer_id"
const val SDK_SERVER_URL = "sdk_server_url"
const val MCH_PAR_NO = "merchant_partner_no"
const val SUPPORT_ATTESTATION = "support_attestation"
const val SUPPORT_NETWORK_CHECKING = "support_network_check"
const val SUPPORT_HIDE_ICON = "support_hide_icon"
const val SUPPORT_ELECTRONIC_SIGNATURE = "support_electronic_signature"
const val RECEIPT_FOOTER = "receipt_footer"
const val MERCHANT_ADDRESS_1 = "merchant_address_1"
const val MERCHANT_ADDRESS_2 = "merchant_address_2"
const val MERCHANT_ADDRESS_3 = "merchant_address_3"
const val MERCHANT_UUID = "merchant_uuid"
const val BACKEND_SOFTWARE_VER = "backend_software_ver"
const val HELP_SUPPORT_URL = "help_support_url"
const val SUPPORT_PAYMENT_SELECTION = "support_payment_selection"
const val SUPPORT_SETTLEMENT = "support_manual_settlement"
const val REVERSAL_RETRY_TIMES = "reversal_retry_times"
const val TXN_CURRENCY_TEXT = "transaction_currency_text"
const val ENABLE_SLOW_READER_CARD_ANIMATION = "enable_slow_read_card_animation"
const val DOWNLOADED_MERCHANT_LOGO_DATA = "downloaded_merchant_logo_data"
const val MERCHANT_LOGO_FILE_NAME = "merchant_logo_file_name"
const val DOWNLOADED_MERCHANT_LOGO_URL = "downloaded_merchant_logo_url"
const val SUPPORT_DOWNLOAD_CONFIGS_BEFORE_TRANS = "support_download_config_before_trans"
const val ENABLE_SCREENSHOT = "enable_screenshot"
const val LANGUAGE_OPTION = "language_option"
const val ENABLE_EXTERNAL_MPOS_CARD_READER = "enable_external_mpos_card_reader"
const val ENABLE_SETTLEMENT = "enable_settlement_txn"
const val ENABLE_CUSTOMIZED_QR = "enable_customized_qr"
const val ENABLE_AUTHORIZATION = "enable_auth_txn"

const val EXTRA_PAYMENT_METHOD = "paymentMethod"

const val EXTRA_TRANS_AMOUNT = "transAmount"

const val EXTRA_TRANS_STATUS = "transStatus"

const val EXTRA_SETTLE_STATUS = "settleStatus"

const val EXTRA_TRANS_ERROR_CODE_MSG = "transErrorMsg"

const val EXTRA_TRANS_NO = "txnNo"

const val EXTRA_TRANS_TC = "emvTc"

const val EXTRA_MASK_PAN = "maskPan"

const val EXTRA_AUTH_CODE = "authCode"

const val EXTRA_INVOICE_NO = "invoice_number"

const val EXTRA_DATE_TIME = "dateTIme"

const val EXTRA_CARD_BRAND = "cardBrand"

const val EXTRA_MERCHANT_ID = "merchantId"

const val EXTRA_TERMINAL_ID = "terminalId"

const val EXTRA_PAY_TRANSACTION_ID = "transactionId"

//App2App mode
const val MHD_EXTRA_APP_MODE = "mhd_extra_app_mode"

const val MHD_EXTRA_MERCH_PARTNER_NO = "mhd_extra_merchant_partner_no"

const val MHD_EXTRA_APP_ID = "mhd_extra_app_id"

const val MHD_EXTRA_PAY_METHOD = "mhd_extra_pay_method"

const val MHD_EXTRA_TRANS_TYPE = "mhd_extra_trans_type"

const val MHD_EXTRA_TXN_AMOUNT = "mhd_extra_trans_amount"

const val MHD_EXTRA_TIP_AMOUNT = "mhd_extra_tip_amount"

const val MHD_EXTRA_TOTAL_AMOUNT = "mhd_extra_total_amount"

const val MHD_EXTRA_RESPONSE_CODE = "mhd_extra_response_code"

const val MHD_EXTRA_RESPONSE_MSG = "mhd_extra_response_msg"

const val MHD_EXTRA_ACQ_NAME = "mhd_extra_acquirer_name"

const val MHD_EXTRA_ISSUER_NAME = "mhd_extra_issuer_name"

const val MHD_EXTRA_ACQ_MERCHANT_NAME = "mhd_extra_merchant_name"

const val MHD_EXTRA_ACQ_MERCHANT_ID = "mhd_extra_merchant_id"

const val MHD_EXTRA_ACQ_TERMINAL_ID = "mhd_extra_terminal_id"

const val MHD_EXTRA_PAY_ORDER_ID = "mhd_extra_order_id"

const val MHD_EXTRA_TRANS_NO = "mhd_extra_trans_no"

const val MHD_EXTRA_ORG_TRANS_NO = "mhd_extra_original_trans_no"

const val MHD_EXTRA_BATCH_NO = "mhd_extra_batch_no"

const val MHD_EXTRA_INVOICE_NO = "mhd_extra_invoice_no"

const val MHD_EXTRA_MASKED_PAN = "maskPan"

const val MHD_EXTRA_REFERENCE_NO = "refNo"

const val MHD_EXTRA_DATE_TIME = "dateTIme"

const val MHD_EXTRA_AUTH_CODE = "authCode"

const val MHD_EXTRA_AID = "emvAid"

const val MHD_EXTRA_TRANS_TC = "emvTc"

const val MHD_EXTRA_TRANS_TVR = "emvTvr"

const val MHD_EXTRA_TRANS_TSI = "emvTsi"

const val MHD_EXTRA_TRANS_ATC = "emvAtc"

const val MHD_EXTRA_APP_LABEL = "emvAppName"

const val MHD_EXTRA_TRANS_CVM = "emv_cvm_flag"

const val MHD_EXTRA_SIGNATURE_SVG = "signature_svg"

const val MHD_EXTRA_TRACE_NO = "trace_number"

const val MHD_EXTRA_BITMAP_IMAGE = "receipt_image"

const val MHD_EXTRA_PAY_DESCRIPTION = "pay_description"

const val MHD_EXTRA_QR_ISSUER_CODE = "qr_issuer_code"

const val MHD_EXTRA_ACTIVATION_CODE = "mhd_extra_activation_code"

const val MHD_EXTRA_TRANS_CURRENCY = "mhd_extra_trans_currency"

const val MDH_EXTRA_SETTLE_BATCHES = "mhd_extra_settle_batches"

const val MDH_EXTRA_PAY_ID = "mhd_extra_pay_id"

const val MHD_EXTRA_ENTRY_MODE = "mhd_extra_entry_mode"

const val MHD_EXTRA_MESSAGE_ID = "mhd_extra_message_id"

const val MHD_EXTRA_CREATE_TIME = "mhd_extra_create_time"

const val MHD_EXTRA_AUTO_DISMISS_RESULT = "mhd_extra_auto_dismiss_result"

val CURRENCY_SYMBOL_MAP: Map<String, String> = mapOf(
    "USD" to "$",
    "CNY" to "¥",
    "TWD" to "NT$",
    "SGD" to "$",
    "EUR" to "€",
    "AUD" to "$",
    "HKD" to "$",
    "GBP" to "£",
    "JPY" to "¥",
    "KWD" to "KD",
    "MYR" to "RM",
    "AED" to "DH",
    "MOP" to "$",
    "TRY" to "₺",
    "LKR" to "₨"
)

val PAYMENT_METHOD_MAP: Map<String, String> = mapOf(
    "01" to "Visa",
    "02" to "Mastercard",
    "03" to "UnionPay",
    "04" to "American Express",
    "05" to "Discover",
    "06" to "JCB",
    "A1" to "WeChat",
    "A2" to "Alipay",
    "B1" to "Visa QR",
    "B2" to "Mastercard QR",
    "B3" to "UnionPay QR",
    "C1" to "ShopeePay",
    "C2" to "PayNow",
    "C3" to "LinePay QR",
    "C8" to "GrabPay",
    "A3" to "ezPay",
    "C4" to "Pi錢包",
    "C6" to "TaiwanPay",
    "C9" to "橘子支",
    "C10" to "iCashPay",
    "C11" to "iPASSMONEY",
    "C12" to "街口",
    "C13" to "欧付宝",
    "C14" to "全支付",
    "C15" to "全盈Pay",
    "C16" to "悠遊卡"
)
