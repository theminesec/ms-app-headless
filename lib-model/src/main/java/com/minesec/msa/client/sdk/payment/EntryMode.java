package com.minesec.msa.client.sdk.payment;

/**
 * @author eric.song
 * @since 2023/4/21 15:47
 */
public interface EntryMode {
    String NFC_ENTRY = "NFC";

    String EMV_CONTACT_ENTRY = "EMV_CONTACT";

    String GUEST_SCAN = "QR";

    String MERCHANT_SCAN = "Presented QR";

    String E_COMM_ENTRY = "E_COMM";
}
