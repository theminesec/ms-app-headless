package com.minesec.msa.client.sdk.payment.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author eric.song
 * @since 2023/4/21 9:50
 */
@Getter
@Setter
public class DownloadEMVDataAid implements Serializable {
    String aid;

    int kernelId;

    int priority;

    String appVersion;
    /**
     * only for VISA/UPI/Discover/JCB
     */
    String terminalTransactionQualifiers;

    long clFloorLimit;

    long clTransLimit;

    long clCVMLimit;

    String defaultDDOL;

    String defaultTDOL;
    /**
     * only for MC
     */
    String terminalRiskMgmtData;
    /**
     * only for MC,can default as 20
     */
    String kernelConfiguration;
    /**
     * only for MC,default is false
     */
    boolean isNewCVMRuleForMC;
    /**
     * only for MC and provided if isNewCVMRuleForMC is true.
     */
    String cvmCapabilityCVMRequired;
    /**
     * only for MC and provided if isNewCVMRuleForMC is true.
     */
    String cvmCapabilityNoCVM;

}
