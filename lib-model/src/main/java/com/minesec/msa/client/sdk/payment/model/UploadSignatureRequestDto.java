package com.minesec.msa.client.sdk.payment.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author eric.song
 * @since 2024/4/7 9:36
 */
@Getter
@Setter
@Builder
public class UploadSignatureRequestDto {
    String sdkId;

    String transactionId;

    String eSign;

    String transType;

    Long reqTime;

    String apiVersion;

    String sign;

    String signType;
}
