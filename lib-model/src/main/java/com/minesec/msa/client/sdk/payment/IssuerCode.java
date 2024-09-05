package com.minesec.msa.client.sdk.payment;

/**
 * @author eric.song
 * @since 2022/12/19 10:26
 */
public interface IssuerCode {
    String VISA_BRAND = "01";

    String MC_BRAND = "02";

    String UPI_BRAND = "03";

    String AMEX_BRAND = "04";

    String DISCOVER_BRAND = "05";

    String JCB_BRAND = "06";

    String WECHAT = "A1";

    String ALIPAY = "A2";

    String SHOPEE_PAY = "C1";
    String PAY_NOW = "C2";
    String GRAB_PAY = "C8";

    String UNKNOWN = "AA";
}
