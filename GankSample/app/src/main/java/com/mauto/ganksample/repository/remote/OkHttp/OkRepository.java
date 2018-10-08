package com.mauto.ganksample.repository.remote.OkHttp;

import com.mauto.ganksample.repository.Repository;
import com.mauto.ganksample.repository.model.pojo.ErrModel;
import com.mauto.ganksample.repository.model.pojo.LoadingModel;
import com.mauto.ganksample.repository.model.pojo.MsgModel;
import com.mauto.ganksample.repository.request.RemoteRequestParam;
import com.mauto.ganksample.repository.request.RequestParam;
import com.mauto.ganksample.repository.request.UrlRequest;
import com.mauto.ganksample.repository.response.DataResponse;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by haohuidong on 18-9-25.
 */

public class OkRepository implements Repository{

    private OkHttpClient mClient;
    public OkRepository(){
        mClient = new OkHttpClient();
    }

    @Override
    public void fetchData(RequestParam param, final DataResponse dataResponse) {
        final Request request = new UrlRequest.Builder().pushRequestParams((RemoteRequestParam) param).build();
        dataResponse.onAction(new LoadingModel());
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dataResponse.onAction(new ErrModel(e.getMessage()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dataResponse.onAction(new MsgModel(response.body().string()));
            }
        });
    }
}
