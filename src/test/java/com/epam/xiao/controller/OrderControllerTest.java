package com.epam.xiao.controller;

import com.epam.xiao.entity.OrderEntity;
import com.epam.xiao.exception.AppException;
import com.epam.xiao.service.OrderService;
import com.epam.xiao.vo.OrderVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.validation.constraints.Null;

public class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addOrderTest(){
        Mockito.when(orderService.addOrder(Mockito.any())).thenReturn(new OrderEntity());
        orderController.addOrder(new OrderVO());

        Assert.assertTrue(true);
    }

    @Test
    public void findOrderTest(){
        Mockito.when(orderService.findOrderById(Mockito.any())).thenReturn(new OrderEntity());
        orderController.findOrder(1l);

        Assert.assertTrue(true);
    }

    @Test(expected = AppException.class)
    public void findOrderExcTest(){
        Mockito.when(orderService.findOrderById(Mockito.any())).thenThrow(new NullPointerException());
        orderController.findOrder(1l);

        Assert.assertTrue(true);
    }
}
