package com.minesec.msa.client.sdk.payment.model;

/**
 * @author eric.song
 * @since 2024/5/17 14:41
 */
public enum QRPaymentScanMode {

    QR_GUEST_SCAN_MODE(1),

    QR_MERCHANT_SCAN_MODE(2),

    QR_ALL_MODE(3);

    private final int value;

    QRPaymentScanMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
