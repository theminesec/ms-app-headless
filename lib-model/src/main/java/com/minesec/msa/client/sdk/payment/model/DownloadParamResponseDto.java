package com.minesec.msa.client.sdk.payment.model;

import com.minesec.msa.client.sdk.ResultDto;

import java.io.Serializable;

import lombok.Getter;

/**
 * @author eric.song
 * @since 2022/8/26 11:33
 */
@Getter
public class DownloadParamResponseDto extends ResultDto implements Serializable {
    private DownloadParamDto data;
}
