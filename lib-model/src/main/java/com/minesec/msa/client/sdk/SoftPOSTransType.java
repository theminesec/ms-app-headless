package com.minesec.msa.client.sdk;

/**
 * @author eric.song
 * @since 2022/12/19 14:57
 */
public interface SoftPOSTransType {
    String TRANS_SALE = "SALE";

    String TRANS_VOID = "VOID";

    String TRANS_REFUND = "REFUND";

    String TRANS_PRE_AUTH = "AUTH";

    String TRANS_AUTH_COMPLETION = "COMPLETION";

    String TRANS_RECEIPT_PREVIEW = "RECEIPT";

    String ACTIVATION = "ACTIVATION";

    String ENQUIRY_DEVICE_STATUS = "ENQUIRY_STATUS";

    String ENQUIRY_TRANS_STATUS = "ENQUIRY_TRANSACTION_STATUS";

    String SETTLEMENT = "SETTLEMENT";

    String WARM_UP = "WARM_UP";

    String RELOAD_CONFIGS = "RELOAD_CONFIGS";

    String ENQUIRY_BLUETOOTH_CONNECT_STATUS = "ENQUIRY_BLUETOOTH_CONNECT_STATUS";

    String UNKNOWN = "UNKNOWN";
}
