package com.minesec.msa.client.sdk.payment.model;


import com.minesec.msa.client.sdk.ResultDto;

import lombok.Getter;

/**
 * @author eric.song
 * @since 2022/07/05 12:41
 */
@Getter
public class TransactionResponseDto extends ResultDto {

    private String sign;

    private PaymentDataDto data;
}
