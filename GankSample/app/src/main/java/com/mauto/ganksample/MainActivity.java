package com.mauto.ganksample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mauto.ganksample.presenter.IView;
import com.mauto.ganksample.presenter.adapters.MainAdapter;
import com.mauto.ganksample.repository.model.BaseModel;
import com.mauto.ganksample.repository.model.pojo.MainModel;
import com.mauto.ganksample.usecase.MainCase;

public class MainActivity extends AppCompatActivity implements IView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    /////////////////////////////////////////--> 18-9-25 下午3:34 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> init views <-- ↓↓↓/////////////////////////////////////
    private ContentLoadingProgressBar mPbLoading;
    private RecyclerView mRvResource;
    private MainAdapter mAdapter;
    private void initViews() {
        mPbLoading = findViewById(R.id.pb_loading);
        mPbLoading.hide();

        mRvResource = findViewById(R.id.rv_resource);
        mRvResource.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainAdapter(this);
        mRvResource.setAdapter(mAdapter);
    }
    /////////////////////////////////////↑↑↑ --> init views <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-9-25 下午3:32 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> render Ui <-- ↓↓↓/////////////////////////////////////
    MainCase mMainCase;
    public void onClickFetchData(View view) {
        mMainCase = new MainCase();
        mMainCase.renderUi(this);
    }

    @Override
    public void onLoading() {
        mPbLoading.show();
        mRvResource.setVisibility(View.GONE);
    }

    @Override
    public void onFailed(String msg) {
        mPbLoading.hide();
    }

    @Override
    public void onSuccess(final BaseModel model) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPbLoading.hide();
                mRvResource.setVisibility(View.VISIBLE);
                mAdapter.fillData((MainModel) model);
            }
        });
    }
    /////////////////////////////////////↑↑↑ --> render Ui <-- ↑↑↑/////////////////////////////////////
}
