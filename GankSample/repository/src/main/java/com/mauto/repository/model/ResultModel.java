package com.mauto.repository.model;

/**
 * Created by haohuidong on 18-9-25.
 */

public abstract class ResultModel {
    public ModelSate state = ModelSate.INIT;
    public String errMsg;
    public String results;
}
