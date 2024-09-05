package com.minesec.msa.client.sdk.payment;

/**
 * @author eric.song
 * @since 2022/12/19 15:11
 */
public enum PaymentMethods {
    VISA_BRAND("01"),
    MC_BRAND("02"),
    UPI_BRAND("03"),
    AMEX_BRAND("04"),
    DISCOVER_BRAND("05"),
    JCB_BRAND("06"),
    DINERS_CARD("07"),
    APPLE_PAY("08"),
    GOOGLE_PAY("09"),
    SAMSUNG_PAY("10"),
    HUAWEI_PAY("11"),
    MY_DEBIT("12"),
    WECHAT("A1"),
    ALIPAY("A2"),
    EZPAY("A3"),
    VISA_QR("B1"),
    MASTERCARD_QR("B2"),
    UNION_PAY_QR("B3"),
    SHOPEE_PAY("C1"),
    PAY_NOW("C2"),
    LINEPAY("C3"),
    PI("C4"),
    ESUNWALLET("C5"),
    TAIWANPAY("C6"),
    EASYWALLET("C7"),
    GRAB_PAY("C8"),
    GAMA_PAY("C9"),
    ICASH_PAY("C10"),
    IPASSMONEY_PAY("C11"),
    JIEKOU_PAY("C12"),
    OUFUBAO("C13"),
    QUAN_PAY("C14"),
    QUAN_YIN_PAY("C15"),
    XIAOYOU_PAY("C16"),
    UNKNOWN("AA");

    private final String code;

    PaymentMethods(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static String getNameByCode(String code) {
        for (PaymentMethods method : values()) {
            if (method.getCode().equals(code)) {
                return method.name();
            }
        }
        return "UNKNOWN";
    }

    public static PaymentMethods fromCode(String code) {
        for (PaymentMethods method : values()) {
            if (method.getCode().equals(code)) {
                return method;
            }
        }
        return UNKNOWN;
    }
}
