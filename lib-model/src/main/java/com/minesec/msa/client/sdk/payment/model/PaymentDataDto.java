package com.minesec.msa.client.sdk.payment.model;

import lombok.Getter;
import lombok.ToString;

/**
 * @author eric.song
 * @since 2022/7/6 13:35
 */
@Getter
@ToString
public class PaymentDataDto {

    private String errCode;

    private String errMsg;

    private String mchOrderNo;

    private int orderState;

    private String payOrderId;

    private String transactionId;

    private String payData;

    private String acquirerMid;

    private String acquirerTid;

    private String acquirerAuthCode;

    private String acquirerRefNo;

    private String acquirerTraceNo;

    private String acquirerTxnTime;

    private String acquirerBatchNo;

    private String acquirerInvoiceNo;
}
