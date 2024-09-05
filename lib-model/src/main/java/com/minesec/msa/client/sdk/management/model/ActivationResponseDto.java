package com.minesec.msa.client.sdk.management.model;

import com.minesec.msa.client.sdk.ResultDto;
import com.minesec.msa.client.sdk.TMSErrorCode;

import lombok.Getter;

/**
 * @author eric.song
 * @since 2023/4/20 10:25
 */
@Getter
public class ActivationResponseDto extends ResultDto {

    private ActivationData data;

    @Override
    public boolean approved() {
        // Invalid Activation code -10013, App need to display this error info differently.
        return getRespCode().equals("0") || getRespCode().equals(TMSErrorCode.INVALID_ACTIVATION_CODE);
    }
}
