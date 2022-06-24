package com.epam.xiao.service;

import com.epam.xiao.dao.PersonRepository;
import com.epam.xiao.entity.PersonEntity;
import com.epam.xiao.vo.PersonVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public PersonEntity addPerson(PersonVO personVO){

        PersonEntity personEntity = new PersonEntity();

        BeanUtils.copyProperties(personVO, personEntity);

        //save or update
        return personRepository.save(personEntity);

    }
}
