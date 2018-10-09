package com.mauto.repository;


import com.mauto.repository.remote.OkHttp.OkRepository;
import com.mauto.repository.request.LocalRequestParam;
import com.mauto.repository.request.RemoteRequestParam;
import com.mauto.repository.request.RequestParam;
import com.mauto.repository.response.DataResponse;

import io.reactivex.Observable;

public class DataRepository {
    
    /////////////////////////////////////////--> 18-9-25 下午5:26 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> singleton <-- ↓↓↓/////////////////////////////////////
    private DataRepository() {
        mOkRepository = (Repository) new OkRepository();
    }

    private static class InstanceHolder{
        private static DataRepository mInstance = new DataRepository();
    }

    public static DataRepository getInstance() {
        return InstanceHolder.mInstance;
    }
    /////////////////////////////////////↑↑↑ --> singleton <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-9-25 下午5:54 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> remote tools <-- ↓↓↓/////////////////////////////////////
    private Repository mOkRepository, mRetrofitRepository;
    /////////////////////////////////////↑↑↑ --> remote tools <-- ↑↑↑/////////////////////////////////////
    
    public void fetchData(RequestParam params, DataResponse response) {
        if (params instanceof RemoteRequestParam) {// fetch data from remote
            mOkRepository.fetchData(params, response);
        } else if (params instanceof LocalRequestParam) {// fetch data from local database(SP has not been supported).
            // database repository & SP repository;
        }
    }

    public <T> Observable<T> fetchData(RequestParam params, Class<T> clazz) {
        if (params instanceof RemoteRequestParam) {// fetch data from remote
            return mOkRepository.fetchData(params, clazz);
        } else if (params instanceof LocalRequestParam) {// fetch data from local database(SP has not been supported).
            // database repository & SP repository;
            return null;
        }
        return null;
    }
}
