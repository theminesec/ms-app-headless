package com.minesec.msa.client.sdk.payment.model;

import com.minesec.msa.client.sdk.ResultDto;

import lombok.Getter;

/**
 * @author eric.song
 * @since 2022/8/26 13:01
 */
@Getter
public class DownloadSessionKeyResponseDto extends ResultDto {

    private SessionKeyDto data;

}
