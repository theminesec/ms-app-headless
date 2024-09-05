package com.minesec.msa.client.sdk.payment.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author eric.song
 * @since 2024/5/17 20:35
 */
@Getter
@Setter
@Builder
public class QueryQRMethodsRequestDto {
    String sdkId;

    Long reqTime;

    String apiVersion;

    String sign;

    String signType;
}
