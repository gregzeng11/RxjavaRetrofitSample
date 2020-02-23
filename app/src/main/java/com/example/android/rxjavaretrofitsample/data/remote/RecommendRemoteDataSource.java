package com.example.android.rxjavaretrofitsample.data.remote;

import com.example.android.rxjavaretrofitsample.http.GankLoader;
import com.example.android.rxjavaretrofitsample.model.Item;

import java.util.List;

import io.reactivex.Observable;

/**
 * created by zyh
 * on 2019-08-10
 */
public class RecommendRemoteDataSource {
    private GankLoader mGankLoader;

    public RecommendRemoteDataSource() {
        this.mGankLoader = new GankLoader();
    }
    
    public Observable<List<Item>> getBeauties(){
        return mGankLoader.getBeauties();
    }
}
