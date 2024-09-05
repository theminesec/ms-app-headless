package com.minesec.msa.client.sdk.payment.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author eric.song
 * @since 2022/12/19 10:58
 */
@Getter
@Setter
public class EmvDataDto {
    /**
     * Standard Tag value
     */
    private String tag;
    /**
     * the length of value(Hex length)
     */
    private String length;
    /**
     * the value of EMV tag data
     */
    private String value;
}
