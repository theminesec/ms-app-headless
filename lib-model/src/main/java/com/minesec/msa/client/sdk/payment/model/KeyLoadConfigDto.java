package com.minesec.msa.client.sdk.payment.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author eric.song
 * @since 2022/12/14 16:47
 */
@Getter
@Setter
public class KeyLoadConfigDto {
    boolean isKeyLoadingRequired;
    DownloadSessionKeyResponseDto keyResponseDto;
}
