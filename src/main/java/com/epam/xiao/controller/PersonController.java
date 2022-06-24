package com.epam.xiao.controller;

import com.epam.xiao.constants.StatusCodeConstant;
import com.epam.xiao.entity.PersonEntity;
import com.epam.xiao.exception.AppException;
import com.epam.xiao.exception.ResponseVO;
import com.epam.xiao.service.PersonService;
import com.epam.xiao.vo.MemberTypeEnum;
import com.epam.xiao.vo.PersonVO;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    PersonService personService;

    @PostMapping(value = "/add")
    public ResponseVO<PersonEntity> addPerson(@Valid @RequestBody PersonVO personVO){
        logger.debug("start to add person for personId:[{}]", personVO.getPersonId());

        this.validateAndSetMemberType(personVO);
        PersonEntity res = null;
        try {
            res = personService.addPerson(personVO);
        } catch (Exception e) {
            String errorMsg = String.format("error occurs adding person for personId:[%s]",personVO.getPersonId());
            logger.error(errorMsg,e);
            throw new AppException(StatusCodeConstant.SERVER_INTERNAL_ERROR,errorMsg);
        }

        logger.debug("success add person for response:[{}]", new Gson().toJson(res));

        return ResponseVO.ok(res);
    }

    private void validateAndSetMemberType(PersonVO personVO){
        String memberType = MemberTypeEnum.getCodeByType(personVO.getMemberType());
        if(StringUtils.isEmpty(memberType)){
            String errorMsg = "person memberType code is not correct,please check!!";
            logger.error(errorMsg);
            throw new AppException("400",errorMsg);
        }else {
            personVO.setMemberType(memberType);
        }
    }
}
