package com.example.android.rxjavaretrofitsample.view;

/**
 * created by zyh
 * on 2019-08-10
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
}