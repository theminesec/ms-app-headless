package com.minesec.msa.client.sdk;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author eric.song
 * @since 2022/12/14 11:48
 */
public class HttpLogger implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String str) {
        Log.d("ERIC_HTTP", str);
    }
}
