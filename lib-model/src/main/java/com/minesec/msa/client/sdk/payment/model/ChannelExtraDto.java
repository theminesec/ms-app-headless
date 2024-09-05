package com.minesec.msa.client.sdk.payment.model;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author eric.song
 * @since 2022/12/19 11:02
 */
@Builder
@Getter
@Setter
public class ChannelExtraDto {

    private long txnAmount;

    private long tipAmount;

    private long totalAmount;

    private boolean dccFlag;

    private long dccAmount;

    private String dccMarkup;

    private String dccRate;

    private String txnNo;

    private String refNo;

    private String authCode;

    private String invoiceNo;

    private String batchNo;

    private String orgTxnNo;

    private String orgRefNo;

    private String orgInvoiceNo;

    private String pan;

    private String maskedPan;

    private String expData;

    private String cvv;

    private String tpdu;

    private String issuerCode;

    private String tc;

    private String cardKeyId;

    private String pinKeyId;

    private String pinBlockPan;

    private Map<String, EmvDataDto> emvTagData;

    private String cvmPerformed;
}
