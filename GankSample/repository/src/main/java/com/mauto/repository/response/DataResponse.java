package com.mauto.repository.response;


import com.mauto.repository.model.ResultModel;

/**
 * Created by haohuidong on 18-9-19.
 */

public interface DataResponse<T extends ResultModel> {
    void onAction(T t);
}
