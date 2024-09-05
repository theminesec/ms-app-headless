package com.minesec.msa.client.sdk.payment.model;

import lombok.Getter;

/**
 * @author eric.song
 * @since 2024/5/17 20:48
 */
@Getter
public class QRPaymentCollectionItemDto {
    private String name;
    private String paymentMethod;
    private String entryMode;
    private String logo;
    private String status;
}
