package com.mauto.ganksample.presenter;


import com.mauto.repository.model.BaseModel;

/**
 * Created by haohuidong on 18-9-25.
 */

public interface IView<T extends BaseModel> {
    void onLoading();
    void onFailed(String msg);
    void onSuccess(T model);
}
