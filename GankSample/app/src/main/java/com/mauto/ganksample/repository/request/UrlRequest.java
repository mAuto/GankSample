package com.mauto.ganksample.repository.request;

import android.text.TextUtils;

import com.mauto.ganksample.tools.TextTools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by haohuidong on 18-9-19.
 */

public class UrlRequest{

    public static class Builder{
        // for GET
        private String urlTemplet;

        // for POST
        private String baseUrl;

        private RequestMode mode = RequestMode.GET;
        private HashMap<String, String> params;
        // call first
//        private Builder requestMode(RequestMode requestMode) {
//            mode = requestMode;
//            return this;
//        }
//
//        private Builder addParams(HashMap<String, String> map) {
//            if (params == null)
//                params = new HashMap<>();
//
//            params.putAll((Map<? extends String, ? extends String>) map.clone());
//            return this;
//        }

        public Builder pushRequestParams(RemoteRequestParam requestParam){
            if (requestParam.mode == RequestMode.GET)
                urlTemplet = requestParam.url;
            else
                baseUrl = requestParam.url;

            mode = requestParam.mode;

            if (params == null)
                params = new HashMap<>();
            params.putAll((Map<? extends String, ? extends String>) requestParam.params.clone());

            return this;
        }

        public Request build() {
            Request request = null;

            switch (mode) {
                case GET:{
                    handleGetUrl();
                    if (!TextTools.isEmpty(urlTemplet)) {
                        request = new Request.Builder()
                                .url(urlTemplet)
                                .build();
                    }
                }break;
                case POST:{
                    if (!TextTools.isEmpty(baseUrl) && params != null) {
                        FormBody.Builder builder = new FormBody.Builder();

                        for (String key : params.keySet()) {
                            builder.add(key, String.valueOf(params.get(key)));
                        }

                        request = new Request.Builder()
                                .url(baseUrl)
                                .post(builder.build())
                                .build();
                    }
                }break;
            }

            return request;
        }

        private void handleGetUrl() {
            if (TextTools.isEmpty(urlTemplet)){
                urlTemplet = null;
            }

            if (params == null || params.size() == 0)
                return;

            for (String key : params.keySet()){
                urlTemplet = urlTemplet.replace(key, String.valueOf(params.get(key)));
            }
        }
    }

}
