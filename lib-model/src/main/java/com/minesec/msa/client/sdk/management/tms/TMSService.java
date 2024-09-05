package com.minesec.msa.client.sdk.management.tms;

import com.minesec.msa.client.sdk.ResultDto;
import com.minesec.msa.client.sdk.management.model.ActivationResponseDto;
import com.minesec.msa.client.sdk.management.model.DeviceInfoResponseDto;
import com.minesec.msa.client.sdk.management.model.SettlementHistoryDto;
import com.minesec.msa.client.sdk.payment.model.DownloadParamResponseDto;
import com.minesec.msa.client.sdk.payment.model.DownloadSessionKeyResponseDto;
import com.minesec.msa.client.sdk.payment.model.TransactionDetailsDto;
import com.minesec.msa.client.sdk.payment.model.TransactionHistoryDto;

import java.util.Map;

import io.reactivex.Observable;
import lombok.AllArgsConstructor;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @author eric.song
 * @since 2023/4/19 15:43
 */
public interface TMSService {

    @POST("anon/bind/activation")
    Observable<ActivationResponseDto> activation(@Body ActivationRequest request);

    @DELETE("anon/bind")
    Observable<ResultDto> logout(@HeaderMap Map<String, String> headers);

    @POST("anon/bind/current")
    Observable<DeviceInfoResponseDto> getDeviceInfo(@HeaderMap Map<String, String> headers, @Body GetDeviceInfoRequest request);

    @POST("anon/config/keyload")
    Observable<DownloadSessionKeyResponseDto> downloadKeys(@HeaderMap Map<String, String> headers, @Body DownloadKeyRequest request);

    @POST("anon/config/emv")
    Observable<DownloadParamResponseDto> downloadEMVParam(@HeaderMap Map<String, String> headers, @Body DownloadEMVParamRequest request);

    @GET("anon/order")
    Observable<TransactionHistoryDto> queryAllTransactions(@HeaderMap Map<String, String> headers);

    @GET("anon/order/{transactionId}")
    Observable<TransactionDetailsDto> queryTransactionDetails(@HeaderMap Map<String, String> headers, @Path("transactionId") String transactionId);

    @POST("anon/order/email/{transactionId}")
    Observable<ResultDto> postEmailAddress(@HeaderMap Map<String, String> headers, @Path("transactionId") String transactionId, @Body PostEmailAddressRequest request);

    @GET("anon/settlement")
    Observable<SettlementHistoryDto> getSettlementTotal(@HeaderMap Map<String, String> headers, @Query("type") String type);

    @POST("anon/settlement/email")
    Observable<ResultDto> emailSettlementReport(@HeaderMap Map<String, String> headers, @Body PostEmailSettleRequest request);

    @GET
    @Streaming
    Observable<ResponseBody> downloadMerchantLogo(@Url String imageUrl);

    @AllArgsConstructor
    class ActivationRequest {
        private final String activationCode;
        private final String sdkId;
        private final String deviceName;
    }

    @AllArgsConstructor
    class DownloadKeyRequest {
        private final String sdkId;
        private final String serverUrl;
        private final String customerId;
        private final String licenseId;
        private final boolean update;
    }

    @AllArgsConstructor
    class DownloadEMVParamRequest {
        private final String sdkId;
    }

    @AllArgsConstructor
    class GetDeviceInfoRequest {
        private final String appVersion;

        private final String logoFileName;
    }

    @AllArgsConstructor
    class PostEmailAddressRequest {
        private final String email;
    }

    @AllArgsConstructor
    class PostEmailSettleRequest {
        private final String email;
        private final String batchIds;
    }
}
