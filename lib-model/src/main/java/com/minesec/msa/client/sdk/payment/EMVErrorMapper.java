package com.minesec.msa.client.sdk.payment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author eric.song
 * @since 2024/2/19 15:56
 */
public class EMVErrorMapper {

    public static final Map<String, String> EMV_ERR_OUTCOME_CODE;

    static {
        EMV_ERR_OUTCOME_CODE = new HashMap<>();
        /**
         * Common EMV error code from kernel
         */
        EMV_ERR_OUTCOME_CODE.put("-1", "Invalid card, please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-2", "Card blocked, please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-3", "No matched card application detected for device,please check EMV AID configurations");
        EMV_ERR_OUTCOME_CODE.put("-4", "Transaction was cancelled,please try it again");
        EMV_ERR_OUTCOME_CODE.put("-5", "This error will not happen in the production environment after L2 and L3 certification");
        EMV_ERR_OUTCOME_CODE.put("-6", "Invalid card, please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-7", "The transaction is not accepted by the card issuer,please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-8", "The transaction is declined by the card and cannot perform the transaction,please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-9", "CAPK key expired,please try to update CAPK and try it again");
        EMV_ERR_OUTCOME_CODE.put("-10", "No Pinpad detected");
        EMV_ERR_OUTCOME_CODE.put("-11", "No PIN entry detected");
        EMV_ERR_OUTCOME_CODE.put("-12", "CAPK Key Checksum error,please try to update CAPK and try it again");
        EMV_ERR_OUTCOME_CODE.put("-13", "Invalid card, please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-14", "Invalid card, please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-15", "Exceed limit for number of AID or CAPK");
        EMV_ERR_OUTCOME_CODE.put("-16", "No log entry read from FCI");
        EMV_ERR_OUTCOME_CODE.put("-17", "No record in the transaction log");
        EMV_ERR_OUTCOME_CODE.put("-18", "No log item or log item error");
        EMV_ERR_OUTCOME_CODE.put("-19", "NFC issue,please try it again");
        EMV_ERR_OUTCOME_CODE.put("-20", "Card reading incomplete, please tap and hold card steadily");
        EMV_ERR_OUTCOME_CODE.put("-21", "Invalid card, please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-22", "Invalid card, please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-23", "UPI Specific,RFID failed,please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-24", "UPI Specific,The card is expired,please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-25", "UPI Specific,The card is blacklisted,please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-26", "Invalid card, please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-27", "Invalid card, please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-28", "Invalid card, please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-29", "The transaction amount exceeds the allowed contactless transaction limit,please try with another amount");
        EMV_ERR_OUTCOME_CODE.put("-30", "Null pointer, please contact support");
        EMV_ERR_OUTCOME_CODE.put("-31", "Amount is required,please set amount and try it again");
        EMV_ERR_OUTCOME_CODE.put("-32", "The PIN is blocked,please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-37", "For Mastercard Paypass to output transaction,please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-38", "Invalid card, please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-50", "Invalid card, please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-51", "The transaction is declined by the card and cannot perform the transaction,please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-56", "Invalid card, please change another card and retry");
        /**
         * Amex error code from kernel
         */
        EMV_ERR_OUTCOME_CODE.put("-100", "Card reading incomplete, please tap and hold card steadily");
        EMV_ERR_OUTCOME_CODE.put("-101", "Amex config parameter error,please check EMV configurations");
        EMV_ERR_OUTCOME_CODE.put("-102", "Amex open kernel error,please contact support");
        EMV_ERR_OUTCOME_CODE.put("-103", "Amex init transaction data error,please check Amex configurations");
        EMV_ERR_OUTCOME_CODE.put("-104", "Amex parsing PPSE response error,please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-105", "Amex recovery IC card public key error,please contact support");
        EMV_ERR_OUTCOME_CODE.put("-106", "Amex recovery issuer public key error,please contact support");
        EMV_ERR_OUTCOME_CODE.put("-107", "Amex recovery signature data error,please contact support");

        /**
         * Discover error code from kernel
         */
        EMV_ERR_OUTCOME_CODE.put("-201", "Discover config parameters error,please check EMV configurations");
        EMV_ERR_OUTCOME_CODE.put("-202", "Discover kernel data error,,please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-203", "Discover select next application,,please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-204", "Please try it again");
        EMV_ERR_OUTCOME_CODE.put("-205", "Discover transaction declined,please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-208", "Discover try another interface,please change another card and retry");
        EMV_ERR_OUTCOME_CODE.put("-209", "Discover kernel end the application,,please change another card and retry");

        /**
         * Additional error code from kernel
         */
        EMV_ERR_OUTCOME_CODE.put("255", "No NFC provider,please contact support");
        EMV_ERR_OUTCOME_CODE.put("256", "EMV parameters is NULL,please check EMV configurations");
        EMV_ERR_OUTCOME_CODE.put("257", "EMV transaction auth amount can not be 0.0,please retry with valid amount");
        /**
         * Card data(Track2) encryption error code
         */
        EMV_ERR_OUTCOME_CODE.put("260", "Generate the card data encryption key error,please contact support");
        EMV_ERR_OUTCOME_CODE.put("261", "Encryption card data error,please contact support");
    }
}
