package com.minesec.msa.client.sdk;

import android.util.Log;

import androidx.annotation.NonNull;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * @author eric.song
 * @since 2022/12/16 14:46
 */
public class EmptySingleObserver<T> implements SingleObserver<T> {
    private static final String TAG = "EmptySingleObserver";

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        Log.d(TAG, "onSubscribe");
    }

    @Override
    public void onSuccess(@NonNull T t) {
        Log.d(TAG, "onSuccess");
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Log.d(TAG, "onError " + e.getMessage());
    }
}
