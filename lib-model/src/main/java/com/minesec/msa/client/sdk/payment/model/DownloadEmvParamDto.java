package com.minesec.msa.client.sdk.payment.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author eric.song
 * @since 2022/8/26 11:30
 */
@Getter
@Setter
public class DownloadEmvParamDto implements Serializable {

    private String terminalType;

    private String terminalCapability;

    private String terminalAddiCapability;

    private String terminalCountryCode;

    private String txnCurrencyCode;

    private String amexReaderCapability;

    private String amexExReaderCapability;

    private String amexFloorLimit;

    private String emvFlags;

    private String ifdSerialNumber;
}
