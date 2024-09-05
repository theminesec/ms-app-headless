package com.minesec.msa.client.sdk.payment.model;

import com.minesec.msa.client.sdk.ResultDto;

import lombok.Getter;

/**
 * @author eric.song
 * @since 2024/5/17 20:36
 */
@Getter
public class QueryQRMethodsResponseDto extends ResultDto {
    private QRPaymentCollectionDto data;
}
