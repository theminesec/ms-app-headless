package com.minesec.msa.client.sdk;

import android.util.Log;

import androidx.annotation.NonNull;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author eric.song
 * @since 2022/12/16 14:43
 */

public class EmptyObserver<T> implements Observer<T> {

    private static final String TAG = "EmptyObserver";

    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe");
    }

    @Override
    public void onNext(@NonNull T t) {
        Log.d(TAG, "onNext: ");
    }


    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onError " + e.getMessage());
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete");
    }
}
