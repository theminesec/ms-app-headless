package com.minesec.msa.client.sdk.payment;

/**
 * @author eric.song
 * @since 2023/6/2 9:09
 */
public interface TransactionProcessingStatus {

    String ONLINE_PAYMENT = "online_payment";

    String REVERSAL = "reversal";

    String RETRY_TAPPING="retry_tapping";

    String MPOS_DEVICE_CONNECTION = "mpos_device_connection";

    String MPOS_DEVICE_READING_CARD = "mpos_device_card_reading";

    String MPOS_DEVICE_COMPLETE_TXN = "mpos_device_complete_txn";

    String MPOS_DEVICE_LOADING_FILES = "mpos_device_loading_files";
}
