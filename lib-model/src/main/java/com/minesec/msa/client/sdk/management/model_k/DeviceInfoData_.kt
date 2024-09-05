package com.minesec.msa.client.sdk.management.model_k

/**
 * @author eric.song
 * @since 2024/6/18 14:20
 */
data class DeviceInfoData_(
    val sysVersion: String,
    val transType: String,
    val groupId: String,
    val mchName: String,
    val mcc: String,
    val uuid: String,
    val helpSupportUrl: String,
    val currency: String,
    val mchLogo: String,
    val mchContactAddress: String,
    val loadEmvConfig: Boolean,
    val enableExternalReader: Boolean,
    val supportedMPQRMethods: List<String>
)
