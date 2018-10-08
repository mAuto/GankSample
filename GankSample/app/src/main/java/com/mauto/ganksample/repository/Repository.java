package com.mauto.ganksample.repository;

import com.mauto.ganksample.repository.request.RequestParam;
import com.mauto.ganksample.repository.response.DataResponse;

/**
 * Created by haohuidong on 18-9-25.
 */

public interface Repository {
    void fetchData(RequestParam param, DataResponse response);
}
