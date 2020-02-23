package com.example.android.rxjavaretrofitsample.http;

import com.example.android.rxjavaretrofitsample.BuildConfig;
import com.example.android.rxjavaretrofitsample.util.LogUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * created by zyh
 * on 2019-08-10
 */

public class RetrofitManager {
    public static final String TYPE_NORMAL = "type_normal";
    public static final String TYPE_UPLOAD = "type_upload";
    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private Retrofit mRetrofit;
    private static RetrofitManager normalInstance;

    private RetrofitManager() {

        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//读操作超时时间

        // 添加公共参数拦截器
        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
                // .addHeaderParams("uid", "android")
                //   .addHeaderParams("udid", Utils.getAndroidId(BaseApplication.getContext()))
                // .addHeaderParams("deviceid", Utils.getDeviceId(BaseApplication.getContext()))
                .build();
        builder.addInterceptor(commonInterceptor);

        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        // 创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ResponseConvertFactory.create())
                .baseUrl(ApiConfig.BASE_URL)
                .build();
        LogUtil.LOGD("okh", "BaseUrl:" + ApiConfig.BASE_URL);
    }

    /**
     * 获取RetrofitServiceManager
     *
     * @return
     */
    public static RetrofitManager getInstance() {
        if (normalInstance == null) {
            normalInstance = new RetrofitManager();
        }
        return normalInstance;
    }


    /**
     * 获取对应的Service
     *
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }
}
