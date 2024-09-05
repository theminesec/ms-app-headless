package com.minesec.msa.client.sdk.payment.model;

import com.minesec.msa.client.sdk.ResultDto;

import lombok.Getter;
import lombok.ToString;

/**
 * @author eric.song
 * @since 2022/12/14 16:30
 */
@Getter
@ToString
public class TransactionDetailsDto extends ResultDto {
    private TxnData data;

    @Getter
    @ToString
    public static class TxnData extends TxnRecordData {
    }
}
