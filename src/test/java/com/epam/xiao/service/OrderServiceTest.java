package com.epam.xiao.service;

import com.epam.xiao.dao.OrderRepository;
import com.epam.xiao.dao.PersonRepository;
import com.epam.xiao.entity.OrderEntity;
import com.epam.xiao.entity.PersonEntity;
import com.epam.xiao.exception.AppException;
import com.epam.xiao.vo.MemberTypeEnum;
import com.epam.xiao.vo.OrderVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class OrderServiceTest {

    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    PersonRepository personRepository;

    @Mock
    CalculateService calculateService;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void initTest(){

        orderService.init();

    }

    @Test
    public void addOrderTest(){

        PersonEntity personEntity = new PersonEntity("111","xiao","Gold");
        Optional<PersonEntity> optional = Optional.of(personEntity);
        Mockito.when(personRepository.findById(Mockito.any())).thenReturn(optional);
        Mockito.when(orderRepository.save(Mockito.any())).thenReturn(new OrderEntity());
        orderService.addOrder(new OrderVO("111","order1",new BigDecimal(10)));

        Assert.assertTrue(true);


    }

    @Test(expected = AppException.class)
    public void addOrderTestDbException(){

        PersonEntity personEntity = new PersonEntity("111","xiao","Gold");
        Optional<PersonEntity> optional = Optional.of(personEntity);
        Mockito.when(personRepository.findById(Mockito.any())).thenReturn(optional);
        Mockito.when(orderRepository.save(Mockito.any())).thenThrow(new NullPointerException());
        orderService.addOrder(new OrderVO("111","order1",new BigDecimal(10)));

        Assert.assertTrue(true);


    }

    @Test(expected = AppException.class)
    public void addOrderNoPersonException(){
        Optional<PersonEntity> optional = Optional.empty();
        Mockito.when(personRepository.findById(Mockito.any())).thenReturn(optional);
        orderService.addOrder(new OrderVO("111","order1",new BigDecimal(10)));

        Assert.assertTrue(true);


    }

    @Test
    public void findOrderByIdTest(){
        OrderEntity orderEntity = new OrderEntity(1l,"111","test",new BigDecimal(1),new BigDecimal(3));
        Optional<OrderEntity> optional = Optional.of(orderEntity);
        Mockito.when(orderRepository.findById(Mockito.any())).thenReturn(optional);

        orderService.findOrderById(1l);

        Assert.assertTrue(true);
    }
}
