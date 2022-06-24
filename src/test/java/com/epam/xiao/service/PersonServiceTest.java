package com.epam.xiao.service;


import com.epam.xiao.dao.PersonRepository;
import com.epam.xiao.entity.PersonEntity;
import com.epam.xiao.vo.PersonVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PersonServiceTest {

    @InjectMocks
    PersonService personService;

    @Mock
    PersonRepository personRepository;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addPersonTest(){

        PersonVO personVO = new PersonVO("111","xiao","01");

        PersonEntity personEntity = new PersonEntity("111","xiao","01");

        Mockito.when(personRepository.save(new PersonEntity())).thenReturn(personEntity);

       personService.addPerson(personVO);

        Assert.assertEquals(personEntity.getPersonId(),"111");

    }
}
