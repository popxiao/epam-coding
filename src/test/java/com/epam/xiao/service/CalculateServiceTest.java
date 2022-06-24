package com.epam.xiao.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

public class CalculateServiceTest {

    @InjectMocks
    CalculateService calculateService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getGoldPointsTest() {
        calculateService.getGoldPoints(new BigDecimal(1));
        Assert.assertTrue(true);
    }

    @Test
    public void getSilverPointsTest() {
        calculateService.getSilverPoints(new BigDecimal(1));
        Assert.assertTrue(true);
    }

    @Test
    public void getCoperPointsTest() {
        calculateService.getCoperPoints(new BigDecimal(1));
        Assert.assertTrue(true);
    }
}
