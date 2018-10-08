package com.mauto.ganksample.repository.remote.OkHttp;

import com.mauto.ganksample.repository.model.ResultModel;
import com.mauto.ganksample.repository.model.pojo.LoadingModel;
import com.mauto.ganksample.repository.request.RemoteRequestParam;
import com.mauto.ganksample.repository.request.RequestParam;
import com.mauto.ganksample.repository.response.DataResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

/**
 * Created by haohuidong on 18-9-26.
 */
public class OkRepositoryTest {

    OkRepository mOkRepository;
    @Before
    public void setUp() throws Exception {
        mOkRepository = mock(OkRepository.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void fetchData() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                DataResponse reponse = (DataResponse) invocation.getArguments()[1];
                reponse.onAction(new LoadingModel());
                return null;
            }
        }).when(mOkRepository).fetchData(any(RequestParam.class), any(DataResponse.class));

        mOkRepository.fetchData(new RemoteRequestParam(), new DataResponse() {
            @Override
            public void onAction(ResultModel resultModel) {
                System.out.println("mode:"+resultModel.state);
            }
        });
    }

}