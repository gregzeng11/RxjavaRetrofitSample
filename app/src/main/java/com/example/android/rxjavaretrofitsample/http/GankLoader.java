package com.example.android.rxjavaretrofitsample.http;

import com.example.android.rxjavaretrofitsample.model.Item;
import com.example.android.rxjavaretrofitsample.util.GankBeautyResultToItemsMapper;

import java.util.List;

import io.reactivex.Observable;

/**
 * created by zyh
 * on 2019-08-10
 */
public class GankLoader extends ObjectLoader{
    private GankApi mGankApi;
    
    public GankLoader() {
        mGankApi= RetrofitManager.getInstance().create(GankApi.class);
    }
    
    public Observable<List<Item>> getBeauties(){
        return observe(mGankApi.getBeauties(20,2).map(GankBeautyResultToItemsMapper.getInstance())).
                retryWhen(new RetryWithDelay(3, 2000));
    }
}
