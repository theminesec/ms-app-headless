package com.minesec.msa.client.sdk;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author eric.song
 * @since 2022/12/16 14:20
 */
public class NetUtil {
    private static OkHttpClient sHttpClient;

    public static OkHttpClient getHttpClient() {
        if (sHttpClient == null) {
            synchronized (NetUtil.class) {
                if (sHttpClient == null) {
                    sHttpClient = new OkHttpClient();
                }
            }
        }
        return sHttpClient;
    }

    public static Request getPostRequest(String host, String api, String data) {
        MediaType MediaJsonType = MediaType.parse("application/json;charset=utf-8");
        Request request = new Request.Builder()
                .url(host + api)
                .post(RequestBody.create(MediaJsonType, data))
                .build();
        return request;
    }

    public static void callEnqueue(Request request, Callback callback) {
        getHttpClient().newCall(request).enqueue(callback);
    }
}
