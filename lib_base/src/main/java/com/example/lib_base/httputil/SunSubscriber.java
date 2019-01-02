package com.example.lib_base.httputil;


import android.content.Context;

import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.utils.HttpLog;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

import static com.zhouyou.http.utils.Utils.isNetworkAvailable;


public abstract class SunSubscriber<T> extends DisposableObserver<String> {
    public WeakReference<Context> contextWeakReference;

    public SunSubscriber() {
    }

    @Override
    protected void onStart() {
        HttpLog.e("-->http is onStart");
        if (contextWeakReference != null && contextWeakReference.get() != null && !isNetworkAvailable(contextWeakReference.get())) {
            //Toast.makeText(context, "无网络，读取缓存数据", Toast.LENGTH_SHORT).show();
            onComplete();
        }
    }


    public SunSubscriber(Context context) {
        if (context != null) {
            contextWeakReference = new WeakReference<>(context);
        }
    }

    @Override
    public void onNext(@NonNull String s) {
        HttpLog.e("-->http is onNext");
        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T t=GsonUtil.fromJson(s,tClass);
        onNextExecute(t);
    }

    public abstract void onNextExecute(@NonNull T t);

    @Override
    public final void onError(Throwable e) {
        HttpLog.e("-->http is onError");
        if (e instanceof ApiException) {
            HttpLog.e("--> e instanceof ApiException err:" + e);
            onError((ApiException) e);
        } else {
            HttpLog.e("--> e !instanceof ApiException err:" + e);
            onError(ApiException.handleException(e));
        }
    }

    @Override
    public void onComplete() {
        HttpLog.e("-->http is onComplete");
    }


    public abstract void onError(ApiException e);

}
