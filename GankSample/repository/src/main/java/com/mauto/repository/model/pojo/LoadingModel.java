package com.mauto.repository.model.pojo;


import com.mauto.repository.model.ModelSate;
import com.mauto.repository.model.ResultModel;

/**
 * Created by haohuidong on 18-9-25.
 */

public class LoadingModel extends ResultModel {
    public LoadingModel() {
        state = ModelSate.LOADING;
    }
}
