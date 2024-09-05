package com.minesec.msa.client.sdk.payment.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author eric.song
 * @since 2022/12/19 15:10
 */
@Builder
@Getter
@Setter
public class QRTransactionDto {

    private String payDataType;

    private String authCode;
}
