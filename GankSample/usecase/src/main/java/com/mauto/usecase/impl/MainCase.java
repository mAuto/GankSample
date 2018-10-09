package com.mauto.usecase.impl;

import com.mauto.repository.DataRepository;
import com.mauto.repository.model.pojo.GankBean;
import com.mauto.repository.model.pojo.MainModel;
import com.mauto.repository.remote.OkHttp.NetPointer;
import com.mauto.repository.request.RemoteRequestParam;
import com.mauto.repository.request.RequestMode;
import com.mauto.usecase.UseCase;
import com.mauto.usecase.executor.PostExecutor;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by haohuidong on 18-10-9.
 */

public class MainCase extends UseCase<MainModel, Void> {

    public MainCase(PostExecutor postExecutor) {
        super(postExecutor);
    }

    @Override
    protected Observable<MainModel> buildUseCaseObservable(Void aVoid) {
        RemoteRequestParam params = new RemoteRequestParam();
        params.mode = RequestMode.GET;
        params.url = NetPointer.POINTER_GANKIO_ANDROID_GET;
        params.params = new HashMap<>();
        params.params.put(NetPointer.POINTER_GET_PARAM_COUNT, "20");
        params.params.put(NetPointer.POINTER_GET_PARAM_FLAG, "1");
        return DataRepository.getInstance().fetchData(params, MainModel.class);
    }
}
