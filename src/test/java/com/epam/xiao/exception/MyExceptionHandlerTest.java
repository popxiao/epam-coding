package com.epam.xiao.exception;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class MyExceptionHandlerTest {

    @InjectMocks
    MyExceptionHandler myExceptionHandler;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void handleExceptionTest(){
        AppException ae = new AppException("400","error");
        myExceptionHandler.handleException(ae);

        Assert.assertTrue(true);
    }

    @Test
    public void handleExceptionNoErrorMsgTest(){
        AppException ae = new AppException();
        ae.setErrorCode("400");
        myExceptionHandler.handleException(ae);

        Assert.assertTrue(true);
    }
}
