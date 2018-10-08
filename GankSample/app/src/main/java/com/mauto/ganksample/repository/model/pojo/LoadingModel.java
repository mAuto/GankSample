package com.mauto.ganksample.repository.model.pojo;

import com.mauto.ganksample.repository.model.BaseModel;
import com.mauto.ganksample.repository.model.ModelSate;
import com.mauto.ganksample.repository.model.ResultModel;

/**
 * Created by haohuidong on 18-9-25.
 */

public class LoadingModel extends ResultModel {
    public LoadingModel() {
        state = ModelSate.LOADING;
    }
}
