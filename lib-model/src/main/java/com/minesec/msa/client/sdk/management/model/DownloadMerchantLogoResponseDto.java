package com.minesec.msa.client.sdk.management.model;


import lombok.Getter;
import lombok.Setter;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

/**
 * @author eric.song
 * @since 2023/6/20 13:42
 */
@Getter
@Setter
public class DownloadMerchantLogoResponseDto extends ResponseBody {

    private final ResponseBody responseBody;

    public DownloadMerchantLogoResponseDto(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        return responseBody.source();
    }
}
