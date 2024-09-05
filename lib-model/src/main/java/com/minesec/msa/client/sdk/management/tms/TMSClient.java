package com.minesec.msa.client.sdk.management.tms;

import androidx.annotation.NonNull;

import com.minesec.msa.client.sdk.Client;
import com.minesec.msa.client.sdk.ClientOpts;
import com.minesec.msa.client.sdk.ResultDto;
import com.minesec.msa.client.sdk.management.model.ActivationResponseDto;
import com.minesec.msa.client.sdk.management.model.DeviceInfoResponseDto;
import com.minesec.msa.client.sdk.management.model.DownloadMerchantLogoResponseDto;
import com.minesec.msa.client.sdk.management.model.SettlementHistoryDto;
import com.minesec.msa.client.sdk.payment.model.DownloadParamResponseDto;
import com.minesec.msa.client.sdk.payment.model.KeyLoadConfigDto;
import com.minesec.msa.client.sdk.payment.model.TransactionDetailsDto;
import com.minesec.msa.client.sdk.payment.model.TransactionHistoryDto;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * @author eric.song
 * @since 2023/4/19 15:42
 */
public class TMSClient extends Client {
    private final String url;
    private final TMSService service;

    public TMSClient(String url) {
        this.url = url;
        this.service = startService(TMSService.class,
                url, ClientOpts.DEFAULT_CONNECT_TIMEOUT, ClientOpts.DEFAULT_READ_TIMEOUT);
    }

    public Observable<ActivationResponseDto> activation(@NonNull String activationCode, @NonNull String sdkId, @NonNull String deviceName) {
        return service.activation(new TMSService.ActivationRequest(activationCode, sdkId, deviceName))
                .map(dto -> {
                    if (!dto.approved()) {
                        throw new RuntimeException(dto.toErrorMessage());
                    }
                    return dto;
                });
    }

    public Observable<ResultDto> logout(@NonNull String token) {
        Map<String, String> headers = buildHeaders(token);
        return service.logout(headers)
                .map(dto -> {
                    if (!dto.approved()) {
                        throw new RuntimeException(dto.toErrorMessage());
                    }
                    return dto;
                });
    }

    public Observable<DeviceInfoResponseDto> getDeviceInfo(@NonNull String token, @NonNull String appVersion, String logoFileName) {
        Map<String, String> headers = buildHeaders(token);
        return service.getDeviceInfo(headers, new TMSService.GetDeviceInfoRequest(appVersion, logoFileName))
                .map(dto -> dto);
    }

    public Observable<KeyLoadConfigDto> downloadKeys(@NonNull String token, @NonNull String sdkId, @NonNull String serverUrl, @NonNull String customerId, String licenseId, boolean update) {
        Map<String, String> headers = buildHeaders(token);
        return service.downloadKeys(headers, new TMSService.DownloadKeyRequest(sdkId, serverUrl, customerId, licenseId, update))
                .map(dto -> {
                    if (!dto.approved()) {
                        throw new RuntimeException(dto.toErrorMessage());
                    }
                    KeyLoadConfigDto keyLoadConfigDto = new KeyLoadConfigDto();
                    keyLoadConfigDto.setKeyLoadingRequired(true);
                    keyLoadConfigDto.setKeyResponseDto(dto);
                    return keyLoadConfigDto;
                });
    }

    public Observable<DownloadParamResponseDto> downloadEMVParam(@NonNull String token, @NonNull String sdkId) {
        Map<String, String> headers = buildHeaders(token);
        return service.downloadEMVParam(headers, new TMSService.DownloadEMVParamRequest(sdkId))
                .map(dto -> {
                    if (!dto.approved()) {
                        throw new RuntimeException(dto.toErrorMessage());
                    }
                    return dto;
                });
    }

    public Observable<TransactionHistoryDto> queryAllTransactions(@NonNull String token) {
        Map<String, String> headers = buildHeaders(token);
        return service.queryAllTransactions(headers)
                .map(dto -> {
                    if (!dto.approved()) {
                        throw new RuntimeException(dto.toErrorMessage());
                    }
                    return dto;
                });
    }

    public Observable<TransactionDetailsDto> queryTransactionDetails(@NonNull String token, @NonNull String transactionId) {
        Map<String, String> headers = buildHeaders(token);
        return service.queryTransactionDetails(headers, transactionId)
                .map(dto -> {
                    if (!dto.approved()) {
                        throw new RuntimeException(dto.toErrorMessage());
                    }
                    return dto;
                });
    }

    public Observable<ResultDto> postEmailAddress(@NonNull String token, @NonNull String transactionId, @NonNull String email) {
        Map<String, String> headers = buildHeaders(token);
        return service.postEmailAddress(headers, transactionId, new TMSService.PostEmailAddressRequest(email))
                .map(dto -> {
                    if (!dto.approved()) {
                        throw new RuntimeException(dto.toErrorMessage());
                    }
                    return dto;
                });
    }

    public Observable<SettlementHistoryDto> getSettlementTotal(@NonNull String token, @NonNull String type) {
        Map<String, String> headers = buildHeaders(token);
        return service.getSettlementTotal(headers, type)
                .map(dto -> {
                    if (!dto.approved()) {
                        throw new RuntimeException(dto.toErrorMessage());
                    }
                    return dto;
                });
    }

    public Observable<ResultDto> emailSettlementReport(@NonNull String token, @NonNull String batchIds, @NonNull String email) {
        Map<String, String> headers = buildHeaders(token);
        return service.emailSettlementReport(headers, new TMSService.PostEmailSettleRequest(email, batchIds))
                .map(dto -> {
                    if (!dto.approved()) {
                        throw new RuntimeException(dto.toErrorMessage());
                    }
                    return dto;
                });
    }

    public Observable<DownloadMerchantLogoResponseDto> downloadMerchantLogo(String imageUrl) {
        return service.downloadMerchantLogo(imageUrl)
                .map(DownloadMerchantLogoResponseDto::new);
    }

    Map<String, String> buildHeaders(String token) {
        Map<String, String> headers = new HashMap<>();
        headers.put("token", token);
        return headers;
    }

}
