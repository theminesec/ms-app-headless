package com.minesec.msa.client.sdk.payment.model;

import androidx.annotation.NonNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author eric.song
 * @since 2022/07/05 12:41
 */
@Getter
@Setter
@Builder
public class TransactionRequestDto implements Cloneable {

    private String paymentMethod;

    private String entryMode;

    /**
     * Transaction ID for SALE.
     */
    private String transactionId;
    /**
     * One Transaction ID can mapper to multiple payId, for example:refund can be partial and multiple times.
     */
    private String payId;

    private String description;

    private String apiVersion;
    /**
     * merchant Number,M
     */
    private String mchParNo;
    /**
     * application id, M
     */
    private String sdkId;    //TODO
    /**
     * payment order Number, M(Refund)
     */
    private String payOrderId;
    /**
     * M(Refund)
     */
    private String mchRefundNo;
    /**
     * M(Refund)
     */
    private String refundReason;
    /**
     * merchant order Number,M(Payment)
     */
    private String mchOrderNo;
    /**
     * payment method,M
     */
    private String wayCode;
    /**
     * transaction amount, M
     * 123 means 1.23, 100 means 1.00
     */
    @Builder.Default
    private Long amount = null; //TODO
    /**
     * currency code, M
     */
    private String currency; //TODO
    /**
     * Goods subject, M
     */
    private String subject;
    /**
     * Goods descriptions,M
     */
    private String body;
    /**
     * notify url,O
     */
    private String notifyUrl; //TODO
    /**
     * expired time, O
     */
//    private long expiredTime;
    /**
     * channel data, O
     */
    private String channelExtra; //TODO
    /**
     * timestamp with length as 13,M
     */
    private long reqTime; //TODO
    /**
     * API version,fix it as 1.0,M
     */
    private String version;
    /**
     * signature value,M
     */
    private String sign;
    /**
     * SHA-256, M
     */
    private String signType;
    /**
     * VOID or Refund pwd.
     */
    private String adminPwd;
    /**
     * Settle All host request.
     */
    private String batches;

    /**
     * POS message ID(from business app).
     */
    private String posMessageId;

    private String transType;

    @NonNull
    @Override
    public TransactionRequestDto clone() {
        try {
            TransactionRequestDto clone = (TransactionRequestDto) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
