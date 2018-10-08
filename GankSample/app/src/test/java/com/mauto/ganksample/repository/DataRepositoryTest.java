package com.mauto.ganksample.repository;

import com.mauto.ganksample.repository.model.ResultModel;
import com.mauto.ganksample.repository.model.pojo.LoadingModel;
import com.mauto.ganksample.repository.remote.OkHttp.OkRepository;
import com.mauto.ganksample.repository.request.LocalRequestParam;
import com.mauto.ganksample.repository.request.RemoteRequestParam;
import com.mauto.ganksample.repository.response.DataResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

/**
 * Created by haohuidong on 18-9-26.
 */
public class DataRepositoryTest {

    @InjectMocks
    DataRepository mRepository;

    @Mock
    OkRepository mOkRepository;

    @Before
    public void setUp() throws Exception {
        mRepository = DataRepository.getInstance();
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void fetchData() throws Exception {

        RemoteRequestParam varParam = new RemoteRequestParam();
        DataResponse<ResultModel> varResponse = new DataResponse<ResultModel>() {
            @Override
            public void onAction(ResultModel resultModel) {

            }
        };

        DataResponse<LoadingModel> varLoadingResponse = new DataResponse<LoadingModel>() {
            @Override
            public void onAction(LoadingModel resultModel) {

            }
        };

        mRepository.fetchData(varParam, varResponse);
        verify(mOkRepository).fetchData(varParam, varLoadingResponse);

    }

}