package com.minesec.msa.client.sdk.payment.model;

import com.minesec.msa.client.sdk.ResultDto;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

/**
 * @author eric.song
 * @since 2022/12/14 16:24
 */
@Getter
@ToString
public class TransactionHistoryDto extends ResultDto {

    private Data data;

    @Getter
    @ToString
    public static class Data {

        private long total;

        private long current;

        private boolean hasNext;

        private List<TxnRecordData> records;
    }
}
