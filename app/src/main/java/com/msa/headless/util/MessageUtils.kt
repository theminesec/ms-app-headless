package com.msa.headless.util

import com.minesec.msa.client.sdk.SoftPOSTransType
import com.minesec.msa.client.sdk.payment.EMVErrorMapper
import com.minesec.msa.client.sdk.payment.PaymentMethods
import com.msa.headless.ui.CURRENCY_SYMBOL_MAP
import com.msa.headless.ui.PAYMENT_METHOD_MAP
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.util.Currency
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlin.math.abs
import kotlin.random.Random

/**
 * @author eric.song
 * @since 2024/7/31 17:37
 */
object MessageUtils {
    fun arrayCompare(array1: ByteArray?, array2: ByteArray?, maxLength: Int): Boolean {
        if (array1 == null || array2 == null) return false
        for (i in 0 until maxLength) {
            if (array2.getOrNull(i) != array1.getOrNull(i)) {
                return false
            }
        }
        return true
    }

    fun getFormatLength(value: String): String {
        val length = value.length / 2
        return length.toString().padStart(2, '0')
    }

    fun getRandomAuthCode(): String {
        return String.format("%06d", Random.nextInt(1000000))
    }

    fun generateRandomString(length: Int): String {
        val charset = "0123456789"
        return (1..length).map { charset[Random.nextInt(charset.length)] }.joinToString("")
    }

    fun getFormatTransactionDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }

    fun getFormatTransDateTime(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }

    fun getLastUpdateTime(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        return dateFormat.format(Date())
    }

    fun convertMineSecDataTime(dateTime: String, isWithSeconds: Boolean): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        return try {
            val date = inputFormat.parse(dateTime)
            val outputFormat = SimpleDateFormat(
                if (isWithSeconds) "yyyy/MM/dd HH:mm:ss" else "yyyy/MM/dd HH:mm",
                Locale.getDefault()
            ).apply {
                timeZone = TimeZone.getDefault()
            }
            date?.let { outputFormat.format(it) }
            ""
        } catch (e: ParseException) {
            e.printStackTrace()
            ""
        }
    }

    fun getGMTDetails(): String {
        val zoneId = ZoneId.systemDefault()
        val zoneOffset = zoneId.rules.getOffset(Instant.now())
        val hours = zoneOffset.totalSeconds / 3600
        val sign = if (hours > 0) "+" else "-"
        return "(GMT$sign${abs(hours)})"
    }

    fun getReceiptTxnDate(dateTime: String?): String {
        return dateTime?.split(" ")?.getOrNull(0) ?: ""
    }

    fun getReceiptTxnTime(dateTime: String?): String {
        return dateTime?.split(" ")?.getOrNull(1) ?: ""
    }

    fun getFormatMaskedPan(maskedPan: String?): String {
        return if (maskedPan.isNullOrEmpty() || maskedPan.length < 11) {
            "N/A"
        } else {
            "**** **** **** ${maskedPan.takeLast(4)}"
        }
    }

    private fun hexToAscii(hexString: String): String {
        val sb = StringBuilder()
        for (i in 0 until hexString.length step 2) {
            val hex = hexString.substring(i, i + 2)
            val asciiValue = hex.toInt(16)
            sb.append(asciiValue.toChar())
        }
        return sb.toString()
    }

    fun getFormatAppName(appName: String?): String {
        return appName?.let { hexToAscii(it) } ?: "Unknown"
    }

    fun getLongAmount(amount: String): Long {
        return amount.replace(".", "").replace(",", "").toLong()
    }

    fun getTransactionCurrencySymbol(currencyCode: String): String {
        val symbol = Currency.getInstance(currencyCode).symbol
        val customSymbol = CURRENCY_SYMBOL_MAP[currencyCode]
        return if (symbol.length > 1 && customSymbol != null) {
            customSymbol
        } else {
            symbol
        }
    }

    fun getBatchString(num: Int): String {
        return when {
            num < 1 -> ""
            num == 1 -> "1 batch"
            else -> "$num batches"
        }
    }
//
    fun getFormatTransType(transType: String?): String {
        return transType?.toUpperCase(Locale.ROOT) ?: SoftPOSTransType.UNKNOWN
    }

    fun getEMVErrorMessage(code: String?): String {
        return code?.let { EMVErrorMapper.EMV_ERR_OUTCOME_CODE[it] } ?: ""
    }
//
//    fun getFormatErrorMessage(throwable: Throwable?): String {
//        return when {
//            throwable == null -> ApplicationReference.get().getString(R.string.system_error_message)
//            throwable is IOException || throwable is GeneralSecurityException -> ApplicationReference.get().getString(R.string.network_error_message)
//            throwable is TransactionProcessingException || throwable is ReversalTransactionException || throwable is TimeoutTransactionException -> throwable.message ?: ApplicationReference.get().getString(R.string.system_error_message)
//            else -> ApplicationReference.get().getString(R.string.system_error_message)
//        }
//    }
//
//    fun getFormatEMVErrorCode(code: String?): Int {
//        return code?.toIntOrNull()?.let { AppCallAppEMVErrorMapper.APP2APP_EMV_ERR_OUTCOME_CODE[it] }
//            ?: AppCallAppErrorCode.ERR_UNEXPECTED.code
//    }
//
    fun formatPaymentMethodName(paymentMethodCode: String): String {
        return if (paymentMethodCode.isEmpty()) {
            ""
        } else {
            PAYMENT_METHOD_MAP[paymentMethodCode]
                ?: PaymentMethods.getNameByCode(paymentMethodCode)
        }
    }

}