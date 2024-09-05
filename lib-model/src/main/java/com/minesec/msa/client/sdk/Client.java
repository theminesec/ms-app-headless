package com.minesec.msa.client.sdk;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author eric.song
 * @since 2022/12/14 11:45
 */
public abstract class Client {
    protected <T> T startService(Class<T> service,
                                 String baseUrl,
                                 long connectTimeout,
                                 long readTimeout) {
        OkHttpClient client = httpClient(connectTimeout, readTimeout);
        return startService(service, baseUrl, client);
    }

    private <T> T startService(Class<T> service, String baseUrl, OkHttpClient client) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.addConverterFactory(getConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(fixBaseUrl(baseUrl))
                .client(client);
        return builder.build().create(service);
    }

    private static OkHttpClient httpClient(long connectTimeout, long readTimeout) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectionSpecs(Collections.singletonList(ConnectionSpec.RESTRICTED_TLS))
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(getLoggingInterceptor());
        return builder.build();
    }

    private static Interceptor getLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLogger())
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private static String fixBaseUrl(String baseUrl) {
        return baseUrl.endsWith("/") ? baseUrl : (baseUrl + "/");
    }

    protected Converter.Factory getConverterFactory() {
        return GsonConverterFactory.create();
    }

}
