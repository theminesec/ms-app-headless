package com.minesec.msa.client.sdk.payment.model;

import java.util.ArrayList;

import lombok.Getter;

/**
 * @author eric.song
 * @since 2024/5/17 20:37
 */
@Getter
public class QRPaymentCollectionDto {
    private ArrayList<QRPaymentCollectionItemDto> payment_collection;
}
