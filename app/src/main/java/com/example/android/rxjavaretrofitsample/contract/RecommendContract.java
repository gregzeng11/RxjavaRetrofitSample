package com.example.android.rxjavaretrofitsample.contract;

import com.example.android.rxjavaretrofitsample.model.Item;
import com.example.android.rxjavaretrofitsample.presenter.BasePresenter;
import com.example.android.rxjavaretrofitsample.view.BaseView;

import java.util.List;

/**
 * created by zyh
 * on 2019-08-10
 */
public interface RecommendContract {
    interface View extends BaseView<Presenter> {
        void setNewRecommendInfoList(List<Item> songList);
        void showLoading();
        void dismissLoading();
        void showError();
    }

    interface Presenter extends BasePresenter {
        void fetchNewRecommendInfoSource();
        void loadLocalSource();
    }
}
