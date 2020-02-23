package com.example.android.rxjavaretrofitsample.http;


import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;


/**
 * created by zyh
 * on 2019-08-10
 */
public class RetryWithDelay implements Function<Observable<Throwable>, ObservableSource<?>> {
    private final int maxRetries;
    private final int retryDelayMillis;
    private int retryCount;

    public RetryWithDelay(int maxRetries, int retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }

    @Override
    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                //如果失败的原因是UnknownHostException（DNS解析失败，当前无网络），则没必要重试，直接回调error结束请求即可
                if (throwable instanceof UnknownHostException) {
                    return Observable.error(throwable);
                }

                //没超过最大重试次数的话则进行重试
                if (++retryCount <= maxRetries) {
                    //延迟retryDelaySeconds后开始重试
                    return Observable.timer(retryDelayMillis, TimeUnit.SECONDS);
                }

                return Observable.error(throwable);
            }
        });
    }
}
