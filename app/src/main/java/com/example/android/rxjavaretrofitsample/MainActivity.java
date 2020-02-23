package com.example.android.rxjavaretrofitsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.android.rxjavaretrofitsample.adapter.ItemListAdapter;
import com.example.android.rxjavaretrofitsample.contract.RecommendContract;
import com.example.android.rxjavaretrofitsample.model.Item;
import com.example.android.rxjavaretrofitsample.presenter.RecommendPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by zyh
 * on 2019-08-10
 */
public class MainActivity extends AppCompatActivity implements RecommendContract.View {
    @BindView(R.id.gridRv)
    RecyclerView gridRv;
    
    private RecommendPresenter mPresenter;
    private ItemListAdapter mAdapter = new ItemListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        
        gridRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        gridRv.setAdapter(mAdapter);
        
        mPresenter = new RecommendPresenter(this);
        mPresenter.subscribe();
        mPresenter.fetchNewRecommendInfoSource();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
    }

    @Override
    public void setNewRecommendInfoList(List<Item> songList) {
        mAdapter.setItems(songList);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void setPresenter(RecommendContract.Presenter presenter) {

    }
}
