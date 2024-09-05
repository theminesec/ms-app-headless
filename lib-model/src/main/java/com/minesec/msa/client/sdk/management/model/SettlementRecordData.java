package com.minesec.msa.client.sdk.management.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author eric.song
 * @since 2023/6/15 9:31
 */
@Getter
@Setter
public class SettlementRecordData {
    private int state;

    private String batchId;

    private String acquirerMid;

    private String acquirerTid;

    private String currency;

    private String paymentMethod;

    private String profileName;

    private String updatedAt;

    private long paymentCount;

    private long paymentAmt;

    private long refundCount;

    private long refundAmt;

    private long voidCount;

    private long voidAmt;

    private long voidRefundCount;

    private long voidRefundAmt;
}

