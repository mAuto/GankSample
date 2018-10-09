package com.mauto.repository;


import com.mauto.repository.request.RequestParam;
import com.mauto.repository.response.DataResponse;

import io.reactivex.Observable;

/**
 * Created by haohuidong on 18-9-25.
 */

public interface Repository {
    void fetchData(RequestParam param, DataResponse response);
    <T>Observable<T> fetchData(RequestParam param, Class<T> clazz);
}
