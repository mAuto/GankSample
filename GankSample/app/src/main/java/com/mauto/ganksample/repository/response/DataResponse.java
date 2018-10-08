package com.mauto.ganksample.repository.response;

import com.mauto.ganksample.repository.model.BaseModel;
import com.mauto.ganksample.repository.model.ResultModel;

/**
 * Created by haohuidong on 18-9-19.
 */

public interface DataResponse<T extends ResultModel> {
    void onAction(T t);
}
