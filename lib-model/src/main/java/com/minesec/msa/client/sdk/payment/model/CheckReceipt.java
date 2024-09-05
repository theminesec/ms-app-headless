package com.minesec.msa.client.sdk.payment.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author eric.song
 * @since 2023/3/28 13:40
 */
@Getter
@Setter
public class CheckReceipt {
    String merchantName;

    String merchantAddress1;

    String merchantAddress2;

    String merchantAddress3;

    String merchantId;

    String terminalId;

    String currency;

    String amount;

    String cardScheme;

    String maskedPan;

    String expiredDate;

    String txnDateTime;

    String authCode;

    String referenceNo;

    String batchNo;

    String invoiceNo;

    String emvTC;

    String emvAid;

    String emvAtc;

    String emvTvr;

    String emvTsi;

    String emvCvm;

    String footer;

    String appVersion;

    String issuerCode;

    String payDescription;

    String merchantLogo;

    String txnType;

    String traceNo;

    String txnNo;

    String state;

    String appName;
}
