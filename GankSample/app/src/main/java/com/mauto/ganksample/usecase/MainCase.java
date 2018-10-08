package com.mauto.ganksample.usecase;

import com.mauto.ganksample.presenter.IView;
import com.mauto.ganksample.repository.DataRepository;
import com.mauto.ganksample.repository.model.BaseModel;
import com.mauto.ganksample.repository.model.ResultModel;
import com.mauto.ganksample.repository.model.pojo.ErrModel;
import com.mauto.ganksample.repository.model.pojo.LoadingModel;
import com.mauto.ganksample.repository.model.pojo.MainModel;
import com.mauto.ganksample.repository.model.pojo.MsgModel;
import com.mauto.ganksample.repository.remote.OkHttp.NetPointer;
import com.mauto.ganksample.repository.request.RemoteRequestParam;
import com.mauto.ganksample.repository.request.RequestMode;
import com.mauto.ganksample.repository.response.DataResponse;

import java.util.HashMap;

/**
 * Created by haohuidong on 18-9-25.
 */

public class MainCase extends UseCase<MainModel> {

    private DataRepository mRepository = DataRepository.getInstance();

    @Override
    public void renderUi(final IView<MainModel> view) {
        RemoteRequestParam params = new RemoteRequestParam();
        params.mode = RequestMode.GET;
        params.url = NetPointer.POINTER_GANKIO_ANDROID_GET;
        params.params = new HashMap<>();
        params.params.put(NetPointer.POINTER_GET_PARAM_COUNT, "20");
        params.params.put(NetPointer.POINTER_GET_PARAM_FLAG, "1");
        mRepository.fetchData(params, new DataResponse() {
            @Override
            public void onAction(ResultModel model) {
                switch (model.state) {
                    case LOADING:{
                        view.onLoading();
                    }break;
                    case FAILED:{
                        view.onFailed(model.errMsg);
                    }break;
                    case SUCCESS:{
                        MsgModel msgModel = (MsgModel) model;
                        MainModel mainModel = msgModel.parse(MainModel.class);
                        view.onSuccess(mainModel);
                    }break;
                }
            }
        });
    }

    @Override
    protected void fetchData() {

    }
}
