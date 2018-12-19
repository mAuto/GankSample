package com.mauto.ganksample.presenter;

import android.arch.lifecycle.Lifecycle;

import com.mauto.repository.model.BaseModel;

// 做成抽象类或者提供一个通用的抽象类也许比较好
public interface IPresenter<T extends BaseModel> {
    IPresenter bindLifeCycle(Lifecycle lifecycle);
    void fetchData();
    IPresenter setView(IView<T> view);
    void dispose();
}
