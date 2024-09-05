package com.minesec.msa.client.sdk.management.tms

import com.minesec.msa.client.sdk.ResultDto
import com.minesec.msa.client.sdk.management.model_k.ActivationRequest_
import com.minesec.msa.client.sdk.management.model_k.ActivationResponseDto_
import com.minesec.msa.client.sdk.management.model_k.DeviceInfoResponseDto_
import com.minesec.msa.client.sdk.management.model_k.DownloadEMVParamRequest_
import com.minesec.msa.client.sdk.management.model_k.DownloadKeyRequest_
import com.minesec.msa.client.sdk.management.model_k.DownloadKeyResponse_
import com.minesec.msa.client.sdk.management.model_k.DownloadParamResponseDto_
import com.minesec.msa.client.sdk.management.model_k.GetDeviceInfoRequest_
import com.minesec.msa.client.sdk.management.model_k.ResultDto_
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
 * @author eric.song
 * @since 2024/6/18 14:10
 */
interface TMSApiService {

    @POST("anon/bind/activation")
    suspend fun activation(@Body request: ActivationRequest_): ActivationResponseDto_

    @POST("anon/bind/current")
    suspend fun getDeviceInfo(@HeaderMap headers: Map<String, String>, @Body request: GetDeviceInfoRequest_): DeviceInfoResponseDto_

    @POST("anon/config/keyload")
    suspend fun downloadPaymentKeys(@HeaderMap headers: Map<String, String>, @Body request: DownloadKeyRequest_): DownloadKeyResponse_

    @POST("anon/config/emv")
    suspend fun downloadEMVParam(@HeaderMap headers: Map<String, String>, @Body request: DownloadEMVParamRequest_): DownloadParamResponseDto_

    @GET
    @Streaming
    suspend fun downloadMchLogo(@Url imageUrl: String): ResponseBody

    @DELETE("anon/bind")
    suspend fun logout(@HeaderMap headers: Map<String, String>): ResultDto_
}