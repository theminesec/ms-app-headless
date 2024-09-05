package com.minesec.msa.client.sdk.management.model;

import com.minesec.msa.client.sdk.ResultDto;

import lombok.Getter;

/**
 * @author eric.song
 * @since 2023/4/20 11:17
 */
@Getter
public class DeviceInfoResponseDto extends ResultDto {

    private DeviceInfoData data;
}
