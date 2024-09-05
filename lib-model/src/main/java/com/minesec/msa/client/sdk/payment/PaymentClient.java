package com.minesec.msa.client.sdk.payment;


import com.minesec.msa.client.sdk.Client;
import com.minesec.msa.client.sdk.ClientOpts;
import com.minesec.msa.client.sdk.ResultDto;
import com.minesec.msa.client.sdk.payment.model.DownloadParamResponseDto;
import com.minesec.msa.client.sdk.payment.model.KeyLoadConfigDto;
import com.minesec.msa.client.sdk.payment.model.QueryQRMethodsRequestDto;
import com.minesec.msa.client.sdk.payment.model.QueryQRMethodsResponseDto;
import com.minesec.msa.client.sdk.payment.model.TransactionDetailsDto;
import com.minesec.msa.client.sdk.payment.model.TransactionRequestDto;
import com.minesec.msa.client.sdk.payment.model.TransactionResponseDto;
import com.minesec.msa.client.sdk.payment.model.UploadSignatureRequestDto;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * @author eric.song
 * @since 2022/07/05 13:17
 */
public class PaymentClient extends Client {

    private final PaymentService service;

    private final PaymentConfig Config;

    public PaymentClient(PaymentConfig config) {
        this.Config = config;
        this.service = startService(PaymentService.class,
                config.getBaseUrl(),
                config.getConnectTimeout() != 0 ? config.getConnectTimeout() : ClientOpts.DEFAULT_CONNECT_TIMEOUT,
                config.getReadTimeout() != 0 ? config.getReadTimeout() : ClientOpts.DEFAULT_READ_TIMEOUT);
    }

    public Observable<DownloadParamResponseDto> downloadEmvParam(String licenseId, String sdkId) {
        return service.downloadEmvParam(licenseId, sdkId)
                .map(dto -> {
                    if (!dto.approved()) {
                        throw new RuntimeException(dto.toErrorMessage());
                    }
                    return dto;
                });
    }

    public Observable<KeyLoadConfigDto> downloadSessionKey(String customerId, String sdkId) {
        return service.downloadSessionKey(customerId, sdkId, true)
                .map(dto -> {
                    if (!dto.approved()) {
                        throw new RuntimeException(dto.toErrorMessage());
                    }
                    KeyLoadConfigDto cfg = new KeyLoadConfigDto();
                    cfg.setKeyLoadingRequired(true);
                    cfg.setKeyResponseDto(dto);
                    return cfg;
                });
    }

    public Observable<KeyLoadConfigDto> downloadSessionKey(String serverUrl, String customerId, String licenseId, String sdkId, boolean update) {
        return service.downloadSessionKey(serverUrl, customerId, licenseId, sdkId, update)
                .map(dto -> {
                    if (!dto.approved()) {
                        throw new RuntimeException(dto.toErrorMessage());
                    }
                    KeyLoadConfigDto cfg = new KeyLoadConfigDto();
                    cfg.setKeyLoadingRequired(true);
                    cfg.setKeyResponseDto(dto);
                    return cfg;
                });
    }

    public Single<TransactionResponseDto> pay(TransactionRequestDto requestDto) {
        return service.pay(requestDto)
                .map(dto -> dto);
    }

    public Single<TransactionResponseDto> refund(TransactionRequestDto requestDto) {
        return service.refund(requestDto)
                .map(dto -> dto);
    }

    public Single<TransactionResponseDto> voidTrans(TransactionRequestDto requestDto) {
        return service.voidTrans(requestDto)
                .map(dto -> dto);
    }

    public Single<TransactionDetailsDto> query(TransactionRequestDto requestDto) {
        return service.query(requestDto)
                .map(dto -> {
                    if (!dto.approved()) {
                        throw new RuntimeException(dto.toErrorMessage());
                    }
                    return dto;
                });
    }

    public Single<TransactionResponseDto> reversal(TransactionRequestDto requestDto) {
        return service.reversal(requestDto)
                .map(dto -> {
                    if (!dto.approved()) {
                        throw new RuntimeException(dto.toErrorMessage());
                    }
                    return dto;
                });
    }

    public Single<TransactionResponseDto> settlement(TransactionRequestDto requestDto) {
        return service.settlement(requestDto)
                .map(dto -> {
                    if (!dto.approved()) {
                        throw new RuntimeException(dto.toErrorMessage());
                    }
                    return dto;
                });
    }

    public Single<ResultDto> uploadSignature(UploadSignatureRequestDto requestDto) {
        return service.uploadSignature(requestDto)
                .map(dto -> {
                    if (!dto.approved()) {
                        throw new RuntimeException(dto.toErrorMessage());
                    }
                    return dto;
                });
    }

    public Single<QueryQRMethodsResponseDto> querySupportedQRPaymentMethods(QueryQRMethodsRequestDto requestDto) {
        return service.querySupportedQRPaymentMethods(requestDto)
                .map(dto -> {
                    if (!dto.approved()) {
                        throw new RuntimeException(dto.toErrorMessage());
                    }
                    return dto;
                });
    }
}
