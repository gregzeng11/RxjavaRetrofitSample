// (c)2016 Flipboard Inc, All Rights Reserved.

package com.example.android.rxjavaretrofitsample.http;

import com.example.android.rxjavaretrofitsample.model.GankBeauty;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * created by zyh
 * on 2019-08-10
 */
public interface GankApi {
    @GET("data/福利/{number}/{page}")
    Observable<HttpResult<List<GankBeauty>>> getBeauties(@Path("number") int number, @Path("page") int page);
}
