package com.mauto.ganksample.presenter;

import com.mauto.ganksample.repository.model.BaseModel;

/**
 * Created by haohuidong on 18-9-25.
 */

public interface RenderAdapter<T extends BaseModel> {
    void loading();
    void onFailed();
    void onSuccess(T data);
}
