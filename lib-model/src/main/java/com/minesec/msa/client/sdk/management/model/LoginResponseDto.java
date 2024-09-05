package com.minesec.msa.client.sdk.management.model;


import com.minesec.msa.client.sdk.ResultDto;

import lombok.Getter;

/**
 * @author eric.song
 * @since 2022/07/05 14:26
 */
@Getter
public class LoginResponseDto extends ResultDto {

    private LoginData data;
}
