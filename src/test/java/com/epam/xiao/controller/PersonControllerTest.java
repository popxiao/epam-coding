package com.epam.xiao.controller;

import com.epam.xiao.entity.PersonEntity;
import com.epam.xiao.exception.AppException;
import com.epam.xiao.service.PersonService;
import com.epam.xiao.vo.PersonVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PersonControllerTest {

    @InjectMocks
    PersonController personController;

    @Mock
    PersonService personService;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addPersonTest(){
        PersonVO personVO = new PersonVO("111","xiao","01");
        Mockito.when(personService.addPerson(Mockito.any())).thenReturn(new PersonEntity());

        personController.addPerson(personVO);

        Assert.assertTrue(true);
    }

    @Test(expected = AppException.class)
    public void addPersonExceptionTest(){
        PersonVO personVO = new PersonVO("111","xiao","01");
        Mockito.when(personService.addPerson(Mockito.any())).thenThrow(new NullPointerException());

        personController.addPerson(personVO);

        Assert.assertTrue(true);
    }

    @Test(expected = AppException.class)
    public void addPersonValidateExcTest(){
        PersonVO personVO = new PersonVO("111","xiao","04");
        personController.addPerson(personVO);

        Assert.assertTrue(true);

    }

}
