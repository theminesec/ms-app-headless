package com.minesec.msa.client.sdk.management.model;

import com.minesec.msa.client.sdk.ResultDto;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

/**
 * @author eric.song
 * @since 2023/6/15 9:30
 */
@Getter
@ToString
public class SettlementHistoryDto extends ResultDto {
    private Data data;

    @Getter
    @ToString
    public static class Data {

        private int total;

        private int current;

        private boolean hasNext;

        private List<SettlementRecordData> records;
    }
}
