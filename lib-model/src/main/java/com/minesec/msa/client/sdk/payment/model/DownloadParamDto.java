package com.minesec.msa.client.sdk.payment.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;

/**
 * @author eric.song
 * @since 2022/8/26 12:50
 */
@Getter
public class DownloadParamDto implements Serializable {
    /**
     * global terminal EMV parameters
     */
    private DownloadEmvParamDto emvParams;
    /**
     * terminal EMV AID parameters
     */
    private List<DownloadEMVDataAid> emvAID;
}
