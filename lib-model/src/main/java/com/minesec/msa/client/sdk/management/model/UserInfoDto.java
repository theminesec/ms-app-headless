package com.minesec.msa.client.sdk.management.model;

import com.minesec.msa.client.sdk.ResultDto;

import lombok.Getter;
import lombok.ToString;

/**
 * @author eric.song
 * @since 2022/07/05 14:58
 */
@Getter
@ToString
public class UserInfoDto extends ResultDto {

    private UserData data;
}
