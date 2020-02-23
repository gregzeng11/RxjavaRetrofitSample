package com.example.android.rxjavaretrofitsample.presenter;

import com.example.android.rxjavaretrofitsample.contract.RecommendContract;
import com.example.android.rxjavaretrofitsample.data.remote.RecommendRemoteDataSource;
import com.example.android.rxjavaretrofitsample.model.Item;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * created by zyh
 * on 2020-02-16
 */
public class RecommendPresenter implements RecommendContract.Presenter {
    private RecommendContract.View contentView;
    private RecommendRemoteDataSource remoteDataSource;
    private CompositeDisposable mCompositeDisposable;

    public RecommendPresenter(RecommendContract.View contentView) {
        this.contentView = contentView;
        remoteDataSource = new RecommendRemoteDataSource();
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void fetchNewRecommendInfoSource() {
        remoteDataSource.getBeauties().subscribe(new Observer<List<Item>>() {
            @Override
            public void onSubscribe(Disposable d) {
                contentView.showLoading();
                mCompositeDisposable.add(d);
            }

            @Override
            public void onNext(List<Item> items) {
                contentView.dismissLoading();
                contentView.setNewRecommendInfoList(items);
            }

            @Override
            public void onError(Throwable e) {
                contentView.dismissLoading();
                contentView.showError();
            }

            @Override
            public void onComplete() {
                contentView.showLoading();
            }
        });
    }

    @Override
    public void loadLocalSource() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

}
