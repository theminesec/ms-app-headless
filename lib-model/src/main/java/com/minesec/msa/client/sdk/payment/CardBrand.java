package com.minesec.msa.client.sdk.payment;

/**
 * @author eric.song
 * @since 2022/12/19 10:27
 */
public interface CardBrand {
    String VISA_BRAND = "01";

    String MC_BRAND = "02";

    String UPI_BRAND = "03";

    String AMEX_BRAND = "04";

    String DISCOVER_BRAND = "05";

    String JCB_BRAND = "06";

    String UNKNOWN_BRAND = "00";
}
