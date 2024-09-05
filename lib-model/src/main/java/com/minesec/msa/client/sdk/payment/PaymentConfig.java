package com.minesec.msa.client.sdk.payment;

import lombok.Builder;
import lombok.Getter;

/**
 * @author eric.song
 * @since 2022/12/14 16:46
 */
@Getter
@Builder
public class PaymentConfig {
    private final String baseUrl;

    private final String key;

    private final long connectTimeout;

    private final long readTimeout;

    private final String merId;

    private final String appId;

    private final String wayCode;

    private final String currencyText;

    private final String subject;

    private final String version;

    private final String sdkId;

    private final String notifyUrl;

    private final String returnUrl;

    private final String signType;

    private final long expiredTime;

    private final int divisionMode;

    private String merchantPartnerNo;
}