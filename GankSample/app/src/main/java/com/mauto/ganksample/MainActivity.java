package com.mauto.ganksample;

import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mauto.ganksample.presenter.IView;
import com.mauto.ganksample.presenter.adapters.MainAdapter;
import com.mauto.ganksample.presenter.impl.MainPresenter;
import com.mauto.repository.model.BaseModel;
import com.mauto.repository.model.pojo.MainModel;



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
    public void onClickFetchData(View view) {
        fetchDataFromRemote();
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

    /////////////////////////////////////////--> 18-10-9 下午5:58 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> new core <-- ↓↓↓/////////////////////////////////////
    private MainPresenter mPresenter = null;
    private void fetchDataFromRemote() {
//        MainCase main = new MainCase(new PostExecutor() {
//            @Override
//            public Scheduler getScheduler() {
//                return AndroidSchedulers.mainThread();
//            }
//        });
//        main.execute(new DisposableObserver<MainModel>() {
//            @Override
//            public void onNext(MainModel mainModel) {
//                if (mainModel == null)
//                    Log.e("--> size <--", "null");
//                else
//                    Log.e("--> size <--", ""+mainModel.results.size());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        }, null);
        mPresenter = new MainPresenter();
        mPresenter.bindLifeCycle(getLifecycle())
                .setView(this)
                .fetchData();
    }
    /////////////////////////////////////↑↑↑ --> new core <-- ↑↑↑/////////////////////////////////////
}
