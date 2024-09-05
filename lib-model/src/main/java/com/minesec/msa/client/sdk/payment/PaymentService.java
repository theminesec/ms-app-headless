package com.minesec.msa.client.sdk.payment;

import com.minesec.msa.client.sdk.ResultDto;
import com.minesec.msa.client.sdk.payment.model.DownloadParamResponseDto;
import com.minesec.msa.client.sdk.payment.model.DownloadSessionKeyResponseDto;
import com.minesec.msa.client.sdk.payment.model.QueryQRMethodsRequestDto;
import com.minesec.msa.client.sdk.payment.model.QueryQRMethodsResponseDto;
import com.minesec.msa.client.sdk.payment.model.TransactionDetailsDto;
import com.minesec.msa.client.sdk.payment.model.TransactionRequestDto;
import com.minesec.msa.client.sdk.payment.model.TransactionResponseDto;
import com.minesec.msa.client.sdk.payment.model.UploadSignatureRequestDto;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author eric.song
 * @since 2022/12/14 11:51
 */
public interface PaymentService {

    @GET("emv")
    Observable<DownloadParamResponseDto> downloadEmvParam(@Query("licenseId") String licenseId, @Query("sdkId") String sdkId);

    @GET("emv/secret/{customerId}/{sdkId}")
    Observable<DownloadSessionKeyResponseDto> downloadSessionKey(@Path("customerId") String customerId, @Path("sdkId") String sdkId, @Query("update") boolean update);

    @GET("emv/secret")
    Observable<DownloadSessionKeyResponseDto> downloadSessionKey(@Query("serverUrl") String serverUrl,
                                                                 @Query("customerId") String customerId,
                                                                 @Query("licenseId") String licenseId,
                                                                 @Query("sdkId") String sdkId,
                                                                 @Query("update") boolean update);


    @POST("pay/unifiedOrder")
    Single<TransactionResponseDto> pay(@Body TransactionRequestDto requestDto);

    @POST("refund/refundOrder")
    Single<TransactionResponseDto> refund(@Body TransactionRequestDto requestDto);

    @POST("refund/voidOrder")
    Single<TransactionResponseDto> voidTrans(@Body TransactionRequestDto requestDto);

    @POST("pay/query")
    Single<TransactionDetailsDto> query(@Body TransactionRequestDto requestDto);

    @POST("reversal")
    Single<TransactionResponseDto> reversal(@Body TransactionRequestDto requestDto);

    @POST("settlement")
    Single<TransactionResponseDto> settlement(@Body TransactionRequestDto requestDto);

    @POST("pay/esign")
    Single<ResultDto> uploadSignature(@Body UploadSignatureRequestDto requestDto);

    @POST("pay/query/payment-collections")
    Single<QueryQRMethodsResponseDto> querySupportedQRPaymentMethods(@Body QueryQRMethodsRequestDto requestDto);
}