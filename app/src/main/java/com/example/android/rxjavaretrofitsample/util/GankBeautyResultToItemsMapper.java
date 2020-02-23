// (c)2016 Flipboard Inc, All Rights Reserved.

package com.example.android.rxjavaretrofitsample.util;

import com.example.android.rxjavaretrofitsample.http.HttpResult;
import com.example.android.rxjavaretrofitsample.model.GankBeauty;
import com.example.android.rxjavaretrofitsample.model.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.functions.Function;

/**
 * created by zyh
 * on 2019-08-10
 */
public class GankBeautyResultToItemsMapper implements Function<HttpResult<List<GankBeauty>>, List<Item>> {
    private static GankBeautyResultToItemsMapper INSTANCE = new GankBeautyResultToItemsMapper();

    private GankBeautyResultToItemsMapper() {
    }

    public static GankBeautyResultToItemsMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Item> apply(HttpResult<List<GankBeauty>> listHttpResult) throws Exception {
        List<GankBeauty> gankBeauties = listHttpResult.getResults();
        List<Item> items = new ArrayList<>(gankBeauties.size());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        for (GankBeauty gankBeauty : gankBeauties) {
            Item item = new Item();
            try {
                Date date = inputFormat.parse(gankBeauty.createdAt);
                item.description = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                item.description = "unknown date";
            }
            item.imageUrl = gankBeauty.url;
            items.add(item);
        }
        return items;
    }
}
