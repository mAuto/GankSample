package com.mauto.repository.remote.OkHttp;


import com.google.gson.Gson;
import com.mauto.repository.Repository;
import com.mauto.repository.model.pojo.ErrModel;
import com.mauto.repository.model.pojo.LoadingModel;
import com.mauto.repository.model.pojo.MsgModel;
import com.mauto.repository.request.RemoteRequestParam;
import com.mauto.repository.request.RequestParam;
import com.mauto.repository.request.UrlRequest;
import com.mauto.repository.response.DataResponse;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by haohuidong on 18-9-25.
 */

public class OkRepository implements Repository {

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

    @Override
    public <T> Observable<T> fetchData(final RequestParam param, final Class<T> clazz) {

        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(final ObservableEmitter<T> emitter) throws Exception {
                final Request request = new UrlRequest.Builder().pushRequestParams((RemoteRequestParam) param).build();
                mClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        emitter.onError(new Throwable(e.getMessage()));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        emitter.onNext(new Gson().fromJson(response.body().string(), clazz));
                    }
                });
            }
        });
    }
}
