package com.minesec.msa.client.sdk.payment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author eric.song
 * @since 2022/12/14 16:25
 */
@Getter
@Setter
@ToString
public class TxnRecordData {
    private String groupId;
    private String cardMaskedPan;
    private int refundTimes;
    private String deviceId;
    private String acquirerAuthCode;
    private String createdAt;
    private String TVR;
    private String successTime;
    private String receiptAddress;
    private String currency;
    private String acquirerInvoiceNo;
    private int state;
    private String AID;
    private String entryMode;
    private int refundAmount;
    private String updatedAt;
    private String deviceType;
    private int refundState;
    private int amount;
    private String merchantUuid;
    private String acquirerMid;
    private String acquirerTraceNo;
    private String cardExpData;
    private String acquirerTxnTime;
    private String mchName;
    private String TSI;
    private String expiredTime;
    private String transactionId;
    private String TC;
    private int notifyState;
    private String acquirerBatchNo;
    private String clientIp;
    private String paymentMethod;
    private String acquirerTid;
    private String payId;
    private String acquirerRefNo;
    private String APPNAME;
    private int settleState;
    private String transType;
    private String ATC;

    private String posMessageId;

    private String cvmPerformed;
}

