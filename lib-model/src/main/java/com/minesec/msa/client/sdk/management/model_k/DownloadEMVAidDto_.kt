package com.minesec.msa.client.sdk.management.model_k

import java.io.Serializable

/**
 * @author eric.song
 * @since 2024/6/19 15:18
 */
data class DownloadEMVAidDto_(
    val aid: String,
    val kernelId: Int,
    val priority: Int,
    val appVersion: String,
    /**
     * only for VISA/UPI/Discover/JCB
     */
    val terminalTransactionQualifiers: String,
    val clFloorLimit: Long,
    val clTransLimit: Long,
    val clCVMLimit: Long,
    val defaultDDOL: String,
    val defaultTDOL: String,
    /**
     * only for MC
     */
    val terminalRiskMgmtData: String,
    /**
     * only for MC, can default as 20
     */
    val kernelConfiguration: String,
    /**
     * only for MC, default is false
     */
    val isNewCVMRuleForMC: Boolean,
    /**
     * only for MC and provided if isNewCVMRuleForMC is true.
     */
    val cvmCapabilityCVMRequired: String?,
    /**
     * only for MC and provided if isNewCVMRuleForMC is true.
     */
    val cvmCapabilityNoCVM: String?
): Serializable
