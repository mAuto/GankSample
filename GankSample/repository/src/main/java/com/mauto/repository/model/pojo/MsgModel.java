package com.mauto.repository.model.pojo;


import com.google.gson.Gson;
import com.mauto.repository.model.BaseModel;
import com.mauto.repository.model.ModelSate;
import com.mauto.repository.model.ResultModel;
import com.mauto.tools.TextTools;

/**
 * Created by haohuidong on 18-9-25.
 */

public class MsgModel extends ResultModel {
    public MsgModel(String results) {
        state = ModelSate.SUCCESS;
        this.results = results;
    }

    public <T extends BaseModel> T parse(Class<T> clazz) {
        if (TextTools.isEmpty(results))
            return null;
        return new Gson().fromJson(results, clazz);
    }
}
