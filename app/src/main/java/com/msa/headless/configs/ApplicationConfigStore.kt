package com.msa.headless.configs

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.minesec.msa.client.sdk.ClientOpts
import com.msa.headless.APP
import com.msa.headless.ui.AMEX_FLOOR_LIMIT
import com.msa.headless.ui.AMEX_READER_CAPABILITY
import com.msa.headless.ui.AMEX_READER_CAPABILITY_EX
import com.msa.headless.ui.APPLICATION_ID
import com.msa.headless.ui.BACKEND_SOFTWARE_VER
import com.msa.headless.ui.CONNECTION_TIMEOUT
import com.msa.headless.ui.DIVISION_MODE
import com.msa.headless.ui.DOWNLOADED_MERCHANT_LOGO_DATA
import com.msa.headless.ui.DOWNLOADED_MERCHANT_LOGO_URL
import com.msa.headless.ui.EMV_FLAGS
import com.msa.headless.ui.ENABLE_AUTHORIZATION
import com.msa.headless.ui.ENABLE_CUSTOMIZED_QR
import com.msa.headless.ui.ENABLE_EXTERNAL_MPOS_CARD_READER
import com.msa.headless.ui.ENABLE_SCREENSHOT
import com.msa.headless.ui.ENABLE_SETTLEMENT
import com.msa.headless.ui.ENABLE_SLOW_READER_CARD_ANIMATION
import com.msa.headless.ui.HEADLESS_PROFILE_ID
import com.msa.headless.ui.HELP_SUPPORT_URL
import com.msa.headless.ui.LANGUAGE_OPTION
import com.msa.headless.ui.MCH_PAR_NO
import com.msa.headless.ui.MERCHANT_ADDRESS_1
import com.msa.headless.ui.MERCHANT_ADDRESS_2
import com.msa.headless.ui.MERCHANT_ADDRESS_3
import com.msa.headless.ui.MERCHANT_ID
import com.msa.headless.ui.MERCHANT_LOGO_FILE_NAME
import com.msa.headless.ui.MERCHANT_NAME
import com.msa.headless.ui.MERCHANT_UUID
import com.msa.headless.ui.MGMT_HOST_BASE_URL
import com.msa.headless.ui.MGMT_TOKEN
import com.msa.headless.ui.MGMT_TOKEN_BACKUP
import com.msa.headless.ui.NOTIFY_URL
import com.msa.headless.ui.PAYMENT_HOST_BASE_URL
import com.msa.headless.ui.PAYMENT_KEY
import com.msa.headless.ui.READ_CARD_TIMEOUT
import com.msa.headless.ui.RECEIPT_FOOTER
import com.msa.headless.ui.RECEIVE_TIMEOUT
import com.msa.headless.ui.RETURN_URL
import com.msa.headless.ui.REVERSAL_RETRY_TIMES
import com.msa.headless.ui.SDK_CUSTOMER_ID
import com.msa.headless.ui.SDK_ID
import com.msa.headless.ui.SDK_LICENSE_ID
import com.msa.headless.ui.SDK_SERVER_URL
import com.msa.headless.ui.SIGN_TYPE
import com.msa.headless.ui.SP_NAME
import com.msa.headless.ui.SUPPORT_ATTESTATION
import com.msa.headless.ui.SUPPORT_DOWNLOAD_CONFIGS_BEFORE_TRANS
import com.msa.headless.ui.SUPPORT_ELECTRONIC_SIGNATURE
import com.msa.headless.ui.SUPPORT_NETWORK_CHECKING
import com.msa.headless.ui.SUPPORT_PAYMENT_SELECTION
import com.msa.headless.ui.SUPPORT_SETTLEMENT
import com.msa.headless.ui.TERMINAL_CAPABILITY
import com.msa.headless.ui.TERMINAL_COUNTRY_CODE
import com.msa.headless.ui.TERMINAL_ID
import com.msa.headless.ui.TERMINAL_TYPE
import com.msa.headless.ui.TMS_HOST_BASE_URL
import com.msa.headless.ui.TRANS_SUBJECT
import com.msa.headless.ui.TTQ
import com.msa.headless.ui.TXN_CURRENCY_CODE
import com.msa.headless.ui.TXN_CURRENCY_TEXT
import com.msa.headless.ui.VERSION
import com.msa.headless.ui.WAY_CODE

/**
 * @author eric.song
 * @since 2024/6/19 16:18
 */
class ApplicationConfigStore{
    private val sp: SharedPreferences

    private val maxMerchantNameLength = 26

    constructor() {
        sp = APP.instance.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
    }

    constructor(context: Context) {
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
    }

    private constructor(sp: SharedPreferences) {
        this.sp = sp
    }

    fun setManagementHostUrl(value: String): ApplicationConfigStore {
        sp.edit().putString(MGMT_HOST_BASE_URL, value).apply()
        return this
    }

    fun setPaymentHostUrl(value: String): ApplicationConfigStore {
        sp.edit().putString(PAYMENT_HOST_BASE_URL, value).apply()
        return this
    }

    fun setTmsHostUrl(value: String): ApplicationConfigStore {
        sp.edit().putString(TMS_HOST_BASE_URL, value).apply()
        return this
    }

    fun setPaymentKey(value: String): ApplicationConfigStore {
        sp.edit().putString(PAYMENT_KEY, value).apply()
        return this
    }

    fun setToken(value: String): ApplicationConfigStore {
        sp.edit().putString(MGMT_TOKEN, value).putString(MGMT_TOKEN_BACKUP, value).apply()
        return this
    }

    fun setProfileId(value: String?): ApplicationConfigStore {
        sp.edit().putString(HEADLESS_PROFILE_ID,value).apply()
        return this
    }

    fun setConnectionTimeout(value: Int): ApplicationConfigStore {
        sp.edit().putInt(CONNECTION_TIMEOUT, value).apply()
        return this
    }

    fun setReceiveTimeout(value: Long): ApplicationConfigStore {
        sp.edit().putLong(RECEIVE_TIMEOUT, value).apply()
        return this
    }

    fun setTerminalType(value: String): ApplicationConfigStore {
        sp.edit().putString(TERMINAL_TYPE, value).apply()
        return this
    }

    fun setTerminalCapability(value: String): ApplicationConfigStore {
        sp.edit().putString(TERMINAL_CAPABILITY, value).apply()
        return this
    }

    fun setTerminalCountryCode(value: String): ApplicationConfigStore {
        sp.edit().putString(TERMINAL_COUNTRY_CODE, value).apply()
        return this
    }

    fun setCurrencyCode(value: String): ApplicationConfigStore {
        sp.edit().putString(TXN_CURRENCY_CODE, value).apply()
        return this
    }

    fun setCurrencyText(value: String): ApplicationConfigStore {
        sp.edit().putString(TXN_CURRENCY_TEXT, value).apply()
        return this
    }

    fun setTTQ(value: String): ApplicationConfigStore {
        sp.edit().putString(TTQ, value).apply()
        return this
    }

    fun setAmexReaderCapability(value: String): ApplicationConfigStore {
        sp.edit().putString(AMEX_READER_CAPABILITY, value).apply()
        return this
    }

    fun setAmexReaderCapabilityEx(value: String): ApplicationConfigStore {
        sp.edit().putString(AMEX_READER_CAPABILITY_EX, value).apply()
        return this
    }

    fun setAmexTerminalFloorLimit(value: String): ApplicationConfigStore {
        sp.edit().putString(AMEX_FLOOR_LIMIT, value).apply()
        return this
    }

    fun setEmvFlags(value: String): ApplicationConfigStore {
        sp.edit().putString(EMV_FLAGS, value).apply()
        return this
    }

    fun getTmsHostUrl(): String {
        return sp.getString(TMS_HOST_BASE_URL, ApplicationConfigOpts.tmsBaseUrl) ?: ""
    }

    fun getManagementHostUrl(): String {
        return sp.getString(MGMT_HOST_BASE_URL, ApplicationConfigOpts.mgmtHostUrl) ?: ""
    }

    fun getPaymentHostUrl(): String {
        return sp.getString(PAYMENT_HOST_BASE_URL, ApplicationConfigOpts.payHostUrl) ?: ""
    }

    fun getPaymentKey(): String {
        return sp.getString(PAYMENT_KEY, ApplicationConfigOpts.payKey) ?: ""
    }

    fun getToken(): String {
        return if (sp.getString(MGMT_TOKEN, "") == "") sp.getString(MGMT_TOKEN_BACKUP, "") ?: ""
        else sp.getString(MGMT_TOKEN, "") ?: ""
    }

    fun getConnectionTimeout(): Int {
        return sp.getInt(CONNECTION_TIMEOUT, ClientOpts.DEFAULT_CONNECT_TIMEOUT)
    }

    fun getProfileId(): String {
        return sp.getString(HEADLESS_PROFILE_ID,"")  ?: ""
    }

    fun getReceiveTimeout(): Long {
        return sp.getLong(RECEIVE_TIMEOUT, ApplicationConfigOpts.receiveTimeout)
    }

    fun getTerminalType(): String {
        return sp.getString(TERMINAL_TYPE, ApplicationConfigOpts.terminalType) ?: ""
    }

    fun getTerminalCapability(): String {
        return sp.getString(TERMINAL_CAPABILITY, ApplicationConfigOpts.terminalCapability) ?: ""
    }

    fun getTerminalCountryCode(): String {
        return sp.getString(TERMINAL_COUNTRY_CODE, ApplicationConfigOpts.terminalCountryCode) ?: ""
    }

    fun getCurrencyCode(): String {
        return sp.getString(TXN_CURRENCY_CODE, ApplicationConfigOpts.txnCurrencyCode) ?: ""
    }

    fun getAmexReaderCapability(): String {
        return sp.getString(AMEX_READER_CAPABILITY, ApplicationConfigOpts.amexReaderCapability) ?: ""
    }

    fun getAmexReaderCapabilityEx(): String {
        return sp.getString(AMEX_READER_CAPABILITY_EX, ApplicationConfigOpts.amexExReaderCapability) ?: ""
    }

    fun getAmexTerminalFloorLimit(): String {
        return sp.getString(AMEX_FLOOR_LIMIT, ApplicationConfigOpts.amexFloorLimit) ?: ""
    }

    fun getEmvFlags(): String {
        return sp.getString(EMV_FLAGS, ApplicationConfigOpts.emvFlags) ?: ""
    }

    fun setMerchantId(value: String): ApplicationConfigStore {
        sp.edit().putString(MERCHANT_ID, value).apply()
        return this
    }

    fun getMerchantId(): String? {
        return sp.getString(MERCHANT_ID, ApplicationConfigOpts.merchantId)
    }

    fun getMerchantUUID(): String {
        return sp.getString(MERCHANT_UUID, "") ?: ""
    }

    fun setMerchantUUID(value: String): ApplicationConfigStore {
        sp.edit().putString(MERCHANT_UUID, value).apply()
        return this
    }

    fun setMerchantName(value: String): ApplicationConfigStore {
        var newValue = value
        if (!TextUtils.isEmpty(value) && value.length > maxMerchantNameLength) {
            newValue = value.substring(0, maxMerchantNameLength)
        }
        sp.edit().putString(MERCHANT_NAME, newValue).apply()
        return this
    }

    fun getMerchantName(): String? {
        return sp.getString(MERCHANT_NAME, ApplicationConfigOpts.merchantName)
    }

    fun setTerminalId(value: String): ApplicationConfigStore {
        sp.edit().putString(TERMINAL_ID, value).apply()
        return this
    }

    fun getTerminalId(): String? {
        return sp.getString(TERMINAL_ID, ApplicationConfigOpts.terminalId)
    }

    fun setApplicationId(value: String): ApplicationConfigStore {
        sp.edit().putString(APPLICATION_ID, value).apply()
        return this
    }

    fun getApplicationId(): String? {
        return sp.getString(APPLICATION_ID, ApplicationConfigOpts.applicationId)
    }

    fun setWayCode(value: String): ApplicationConfigStore {
        sp.edit().putString(WAY_CODE, value).apply()
        return this
    }

    fun getWayCode(): String {
        return sp.getString(WAY_CODE, ApplicationConfigOpts.wayCode) ?: ""
    }

    fun setVersion(value: String): ApplicationConfigStore {
        sp.edit().putString(VERSION, value).apply()
        return this
    }

    fun getVersion(): String {
        return sp.getString(VERSION, ApplicationConfigOpts.version) ?: ""
    }

    fun setSignType(value: String): ApplicationConfigStore {
        sp.edit().putString(SIGN_TYPE, value).apply()
        return this
    }

    fun getSignType(): String {
        return sp.getString(SIGN_TYPE, ApplicationConfigOpts.signatureType) ?: ""
    }

    fun setDivisionMode(value: Int): ApplicationConfigStore {
        sp.edit().putInt(DIVISION_MODE, value).apply()
        return this
    }

    fun getDivisionMode(): Int {
        return sp.getInt(DIVISION_MODE, ApplicationConfigOpts.divisionMode)
    }

    fun setNotifyUrl(value: String): ApplicationConfigStore {
        sp.edit().putString(NOTIFY_URL, value).apply()
        return this
    }

    fun getNotifyUrl(): String {
        return sp.getString(NOTIFY_URL, ApplicationConfigOpts.notifyUrl) ?: ""
    }

    fun setReturnUrl(value: String): ApplicationConfigStore {
        sp.edit().putString(RETURN_URL, value).apply()
        return this
    }

    fun getReturnUrl(): String {
        return sp.getString(RETURN_URL, ApplicationConfigOpts.returnUrl) ?: ""
    }

    fun setTransSubject(value: String): ApplicationConfigStore {
        sp.edit().putString(TRANS_SUBJECT, value).apply()
        return this
    }

    fun getTransSubject(): String {
        return sp.getString(TRANS_SUBJECT, ApplicationConfigOpts.transSubject) ?: ""
    }

    fun setSdkId(value: String): ApplicationConfigStore {
        sp.edit().putString(SDK_ID, value).apply()
        return this
    }

    fun getSdkId(): String {
        return sp.getString(SDK_ID, "") ?: ""
    }

    fun setSdkLicenceId(value: String): ApplicationConfigStore {
        sp.edit().putString(SDK_LICENSE_ID, value).apply()
        return this
    }

    fun getSdkLicenceId(): String {
        return sp.getString(SDK_LICENSE_ID, "") ?: ""
    }

    fun setSdkCustomerId(value: String): ApplicationConfigStore {
        sp.edit().putString(SDK_CUSTOMER_ID, value).apply()
        return this
    }

    fun getSdkCustomerId(): String {
        return sp.getString(SDK_CUSTOMER_ID, "") ?: ""
    }

    fun setSdkServerUrl(value: String): ApplicationConfigStore {
        sp.edit().putString(SDK_SERVER_URL, value).apply()
        return this
    }

    fun getSdkServerUrl(): String {
        return sp.getString(SDK_SERVER_URL, "") ?: ""
    }

    fun setMerchantPartnerNo(value: String): ApplicationConfigStore {
        sp.edit().putString(MCH_PAR_NO, value).apply()
        return this
    }

    fun getMerchantPartnerNo(): String {
        return sp.getString(MCH_PAR_NO, "") ?: ""
    }

    fun setSupportAttestation(value: Boolean): ApplicationConfigStore {
        sp.edit().putBoolean(SUPPORT_ATTESTATION, value).apply()
        return this
    }

    fun getSupportAttestation(): Boolean {
        return sp.getBoolean(SUPPORT_ATTESTATION, ApplicationConfigOpts.isSupportAttestation)
    }

    fun setSupportNetworkChecking(value: Boolean): ApplicationConfigStore {
        sp.edit().putBoolean(SUPPORT_NETWORK_CHECKING, value).apply()
        return this
    }

    fun getSupportNetworkChecking(): Boolean {
        return sp.getBoolean(SUPPORT_NETWORK_CHECKING, ApplicationConfigOpts.isSupportNetworkChecking)
    }

    fun setSupportElectronicSignature(value: Boolean): ApplicationConfigStore {
        sp.edit().putBoolean(SUPPORT_ELECTRONIC_SIGNATURE, value).apply()
        return this
    }

    fun getSupportElectronicSignature(): Boolean {
        return sp.getBoolean(SUPPORT_ELECTRONIC_SIGNATURE, ApplicationConfigOpts.isSupportElectronicSignature)
    }

    fun setReceiptFooter(value: String): ApplicationConfigStore {
        sp.edit().putString(RECEIPT_FOOTER, value).apply()
        return this
    }

    fun getReceiptFooter(): String {
        return sp.getString(RECEIPT_FOOTER, ApplicationConfigOpts.receiptFooter) ?: ""
    }

    fun setMerchantAddress1(value: String): ApplicationConfigStore {
        sp.edit().putString(MERCHANT_ADDRESS_1, value).apply()
        return this
    }

    fun getMerchantAddress1(): String {
        return sp.getString(MERCHANT_ADDRESS_1, "NO.8,Victoria Road,") ?: ""
    }

    fun setMerchantAddress2(value: String): ApplicationConfigStore {
        sp.edit().putString(MERCHANT_ADDRESS_2, value).apply()
        return this
    }

    fun getMerchantAddress2(): String {
        return sp.getString(MERCHANT_ADDRESS_2, "Central,Hong Kong") ?: ""
    }

    fun setMerchantAddress3(value: String): ApplicationConfigStore {
        sp.edit().putString(MERCHANT_ADDRESS_3, value).apply()
        return this
    }

    fun getMerchantAddress3(): String {
        return sp.getString(MERCHANT_ADDRESS_3, "") ?: ""
    }

    fun setBackendSoftwareVersion(value: String): ApplicationConfigStore {
        sp.edit().putString(BACKEND_SOFTWARE_VER, value).apply()
        return this
    }

    fun getBackendSoftwareVersion(): String {
        return sp.getString(BACKEND_SOFTWARE_VER, "") ?: ""
    }

    fun setHelpSupportUrl(value: String): ApplicationConfigStore {
        sp.edit().putString(HELP_SUPPORT_URL, value).apply()
        return this
    }

    fun getHelpSupportUrl(): String {
        return sp.getString(HELP_SUPPORT_URL, "") ?: ""
    }

    fun setSupportPaymentSelection(value: Boolean): ApplicationConfigStore {
        sp.edit().putBoolean(SUPPORT_PAYMENT_SELECTION, value).apply()
        return this
    }

    fun getSupportPaymentSelection(): Boolean {
        return sp.getBoolean(SUPPORT_PAYMENT_SELECTION, ApplicationConfigOpts.isSupportPaymentMethodSelection)
    }

    fun setSupportSettlement(value: Boolean): ApplicationConfigStore {
        sp.edit().putBoolean(SUPPORT_SETTLEMENT, value).apply()
        return this
    }

    fun getSupportSettlement(): Boolean {
        return sp.getBoolean(SUPPORT_SETTLEMENT, ApplicationConfigOpts.isSupportSettlement)
    }

    fun setReversalRetryTimes(value: Int): ApplicationConfigStore {
        sp.edit().putInt(REVERSAL_RETRY_TIMES, value).apply()
        return this
    }

    fun getReversalRetryTimes(): Int {
        return sp.getInt(REVERSAL_RETRY_TIMES, ApplicationConfigOpts.reversalRetryTimes)
    }

    fun setReadCardTimeout(value: Int): ApplicationConfigStore {
        sp.edit().putInt(READ_CARD_TIMEOUT, value).apply()
        return this
    }

    fun getReadCardTimeout(): Int {
        return sp.getInt(READ_CARD_TIMEOUT, ApplicationConfigOpts.readCardTimeout)
    }

    fun getCurrencyText(): String {
        return sp.getString(TXN_CURRENCY_TEXT, ApplicationConfigOpts.txnCurrencyText) ?: ""
    }

    fun setEnableSlowReaderCardAnimation(value: Boolean): ApplicationConfigStore {
        sp.edit().putBoolean(ENABLE_SLOW_READER_CARD_ANIMATION, value).apply()
        return this
    }

    fun getEnableSlowReaderCardAnimation(): Boolean {
        return sp.getBoolean(ENABLE_SLOW_READER_CARD_ANIMATION, ApplicationConfigOpts.enableSlowReadCardAnimation)
    }

    fun setMerchantLogoFileName(value: String): ApplicationConfigStore {
        sp.edit().putString(MERCHANT_LOGO_FILE_NAME, value).apply()
        return this
    }

    fun getMerchantLogoFileName(): String {
        return sp.getString(MERCHANT_LOGO_FILE_NAME, "") ?: ""
    }

    fun setDownloadMerchantLogoUrl(value: String): ApplicationConfigStore {
        sp.edit().putString(DOWNLOADED_MERCHANT_LOGO_URL, value).apply()
        return this
    }

    fun getDownloadMerchantLogoUrl(): String {
        return sp.getString(DOWNLOADED_MERCHANT_LOGO_URL, "") ?: ""
    }

    fun setMerchantLogoData(value: String): ApplicationConfigStore {
        sp.edit().putString(DOWNLOADED_MERCHANT_LOGO_DATA, value).apply()
        return this
    }

    fun getMerchantLogoData(): String {
        return sp.getString(DOWNLOADED_MERCHANT_LOGO_DATA, "") ?: ""
    }

    fun setSupportDownloadConfigBeforeTransFlag(value: Boolean): ApplicationConfigStore {
        sp.edit().putBoolean(SUPPORT_DOWNLOAD_CONFIGS_BEFORE_TRANS, value).apply()
        return this
    }

    fun getSupportDownloadConfigBeforeTransFlag(): Boolean {
        return sp.getBoolean(SUPPORT_DOWNLOAD_CONFIGS_BEFORE_TRANS, ApplicationConfigOpts.isSupportDownloadConfigBeforeTrans)
    }

    fun setEnableScreenshotFlag(value: Boolean): ApplicationConfigStore {
        sp.edit().putBoolean(ENABLE_SCREENSHOT, value).apply()
        return this
    }

    fun getEnableScreenshotFlag(): Boolean {
        return sp.getBoolean(ENABLE_SCREENSHOT, ApplicationConfigOpts.isEnableScreenshot)
    }

    fun setCurrentLanguageOption(value: String): ApplicationConfigStore {
        sp.edit().putString(LANGUAGE_OPTION, value).apply()
        return this
    }

    fun getCurrentLanguageOption(): String {
        return sp.getString(LANGUAGE_OPTION, "") ?: ""
    }

    fun setEnableExternalMPOSCardReader(value: Boolean): ApplicationConfigStore {
        sp.edit().putBoolean(ENABLE_EXTERNAL_MPOS_CARD_READER, value).apply()
        return this
    }

    fun isEnableExternalMPOSCardReader(): Boolean {
        return sp.getBoolean(ENABLE_EXTERNAL_MPOS_CARD_READER, ApplicationConfigOpts.isEnableExternalCardReader)
    }

    fun setEnableSettlement(value: Boolean): ApplicationConfigStore {
        sp.edit().putBoolean(ENABLE_SETTLEMENT, value).apply()
        return this
    }

    fun getEnableSettlement(): Boolean {
        return sp.getBoolean(ENABLE_SETTLEMENT, ApplicationConfigOpts.isEnableSettlement)
    }

    fun setEnableCustomizedQr(value: Boolean): ApplicationConfigStore {
        sp.edit().putBoolean(ENABLE_CUSTOMIZED_QR, value).apply()
        return this
    }

    fun getEnableCustomizedQr(): Boolean {
        return sp.getBoolean(ENABLE_CUSTOMIZED_QR, ApplicationConfigOpts.isEnableCustomizedMultipleQR)
    }

    fun setEnableAuthTransaction(value: Boolean): ApplicationConfigStore {
        sp.edit().putBoolean(ENABLE_AUTHORIZATION, value).apply()
        return this
    }

    fun isEnableAuthTransaction(): Boolean {
        return sp.getBoolean(ENABLE_AUTHORIZATION, ApplicationConfigOpts.isEnablePreAuth)
    }
}