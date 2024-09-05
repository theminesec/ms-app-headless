package com.msa.headless.configs

import com.minesec.msa.client.sdk.payment.PaymentMethods
import com.minesec.msa.client.sdk.payment.model.QRPaymentScanMode

/**
 * @author eric.song
 * @since 2024/6/19 16:12
 */
object ApplicationConfigOpts {
    const val mgmtHostUrl = "https://merchant.mspayhub.com/api"
    const val payHostUrl = "https://uat-srs.mspayhub.com/api"
    const val tmsBaseUrl = "https://uat-mtms.mspayhub.com/api"
    const val payKey = "WhByBPtUBtSEwyYKlLqqH5NcocHk2ExSNhTAKzuTzAyeWQmrGFL9UjOPa1PEIsu9Biz2fEAJ9UUoepRdgkc9RUJx4vVpVeGfufiiPVv15oRlN3P7rOvVNCDOFaCPyNny"
    const val connectTimeout = 5 * 1000L
    const val receiveTimeout = 65 * 1000L
    const val readCardTimeout = 60 * 1000
    const val merchantId = "M1655978292"
    const val merchantName = "HK-Kwolong-711"
    const val terminalId = "M3FE3E232G"
    const val applicationId = "62b43934e4b086e1ed51eb16"
    const val wayCode = "MS_POS"
    const val version = "1.0"
    const val signatureType = "SHA-256"
    const val expiredTime = 3600L
    const val divisionMode = 0
    const val notifyUrl = "https://pre-payment.mspayhub.com/notify.html"
    const val returnUrl = "https://pre-payment.mspayhub.com/notify.html"
    const val transSubject = "pos mspay"
    const val terminalType = "21"
    const val terminalCountryCode = "0840"
    const val txnCurrencyCode = "0840"
    const val terminalCapability = "0068C8"
    const val amexReaderCapability = "C3"
    const val amexExReaderCapability = "18E00003"
    const val amexFloorLimit = "00000100"
    const val emvFlags = "VMAUJDP"
    const val isSupportAttestation = false
    const val isSupportNetworkChecking = false
    const val isSupportElectronicSignature = false
    const val receiptFooter = "MineSec."
    const val isUsingAppCallApp = false
    const val license = ""
    const val isSupportPaymentMethodSelection = true
    const val isSupportSettlement = false
    const val reversalRetryTimes = 3
    const val txnCurrencyText = "USD"
    const val enableSlowReadCardAnimation = false
    const val isSupportDownloadConfigBeforeTrans = false
    const val isEnableScreenshot = false
    const val customLoadingAnimation = false
    const val isEnableFirebase = false
    const val isEnableExternalCardReader = false
    const val isEnableSettlement = true
    const val isEnablePreAuth = true
    const val isEnableCustomizedMultipleQR = false
//    const val qrPaymentScanMode = QRPaymentScanMode.QR_ALL_MODE.value

    val paymentMethodModelList = listOf(
        PaymentMethodStore.Model("VISA", PaymentMethods.VISA_BRAND.code),
        PaymentMethodStore.Model("MC", PaymentMethods.MC_BRAND.code),
        PaymentMethodStore.Model("UPI", PaymentMethods.UPI_BRAND.code),
        PaymentMethodStore.Model("AMEX", PaymentMethods.AMEX_BRAND.code),
        PaymentMethodStore.Model("JCB", PaymentMethods.JCB_BRAND.code),
        PaymentMethodStore.Model("DISCOVER", PaymentMethods.DISCOVER_BRAND.code),
        PaymentMethodStore.Model("DINERS", PaymentMethods.DINERS_CARD.code),
        PaymentMethodStore.Model("HUAWEI PAY", PaymentMethods.HUAWEI_PAY.code),
        PaymentMethodStore.Model("SAMSUNG PAY", PaymentMethods.SAMSUNG_PAY.code),
        PaymentMethodStore.Model("APPLE PAY", PaymentMethods.APPLE_PAY.code),
        PaymentMethodStore.Model("GOOGLE PAY", PaymentMethods.GOOGLE_PAY.code)
    )
}