package com.mauto.ganksample.presenter.impl;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;

import com.mauto.ganksample.presenter.IPresenter;
import com.mauto.ganksample.presenter.IView;
import com.mauto.repository.model.pojo.MainModel;
import com.mauto.usecase.executor.PostExecutor;
import com.mauto.usecase.impl.MainCase;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;

public class MainPresenter implements IPresenter<MainModel> {

    private Lifecycle mLifecycle = null;
    @Override
    public IPresenter bindLifeCycle(Lifecycle lifecycle) {
        if (lifecycle != null) {
            mLifecycle = lifecycle;
            lifecycle.addObserver(mLifeCycleObserver);
        }
        return this;
    }

    @Override
    public void fetchData() {
        MainCase main = new MainCase(new PostExecutor() {
            @Override
            public Scheduler getScheduler() {
                return AndroidSchedulers.mainThread();
            }
        });
        main.execute(new DisposableObserver<MainModel>() {
            @Override
            protected void onStart() {
                mView.onLoading();
            }

            @Override
            public void onNext(MainModel mainModel) {
                if (mainModel == null) {
                    Log.e("--> size <--", "null");
                    mView.onFailed("");
                } else {
                    Log.e("--> size <--", ""+mainModel.results.size());
                    mView.onSuccess(mainModel);
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.onFailed(e.toString());
            }

            @Override
            public void onComplete() {

            }
        }, null);
    }

    private IView mView = null;
    @Override
    public IPresenter setView(IView<MainModel> view) {
        mView = view;
        return this;
    }

    @Override
    public void dispose() {

    }

    private GenericLifecycleObserver mLifeCycleObserver = new GenericLifecycleObserver() {
        @Override
        public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
            switch (event) {
                case ON_CREATE:{}break;
                case ON_RESUME:{}break;
                case ON_PAUSE:{}break;
                case ON_DESTROY:{
                    dispose();
                    if (mLifecycle != null) {
                        mLifecycle.removeObserver(this);
                    }
                }break;
            }
        }
    };
}
