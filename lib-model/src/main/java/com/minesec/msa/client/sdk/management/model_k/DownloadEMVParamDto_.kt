package com.minesec.msa.client.sdk.management.model_k

import java.io.Serializable


/**
 * @author eric.song
 * @since 2024/6/19 15:04
 */
data class DownloadEMVParamDto_(
    var terminalType: String,
    var terminalCapability: String,
    var terminalAddiCapability: String,
    var terminalCountryCode: String,
    var txnCurrencyCode: String,
    var amexReaderCapability: String,
    var amexExReaderCapability: String,
    var amexFloorLimit: String,
    var emvFlags: String,
    var ifdSerialNumber: String
) : Serializable
