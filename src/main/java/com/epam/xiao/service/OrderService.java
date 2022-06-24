package com.epam.xiao.service;

import com.epam.xiao.constants.StatusCodeConstant;
import com.epam.xiao.dao.OrderRepository;
import com.epam.xiao.dao.PersonRepository;
import com.epam.xiao.entity.OrderEntity;
import com.epam.xiao.entity.PersonEntity;
import com.epam.xiao.exception.AppException;
import com.epam.xiao.vo.MemberTypeEnum;
import com.epam.xiao.vo.OrderVO;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CalculateService calculateService;

    private static Map<String, Function<BigDecimal, BigDecimal>> calculateMap = new HashMap<>();


    @PostConstruct
    public void init(){
        calculateMap.put(MemberTypeEnum.GOLD.getType(),amount -> calculateService.getGoldPoints(amount));
        calculateMap.put(MemberTypeEnum.SILVER.getType(), amount -> calculateService.getSilverPoints(amount));
        calculateMap.put(MemberTypeEnum.COPER.getType(), amount -> calculateService.getCoperPoints(amount));
    }


    public OrderEntity addOrder(OrderVO orderVO){
        String personId = orderVO.getPersonId();
        logger.debug("start to find person from personId:[{}]",personId);
        Optional<PersonEntity> optionalPersonEntity = personRepository.findById(personId);
        if(optionalPersonEntity.isPresent()){
            PersonEntity personEntity = optionalPersonEntity.get();
            String memberType = personEntity.getMemberType();
            OrderEntity orderEntity = new OrderEntity();
            BeanUtils.copyProperties(orderVO, orderEntity);
            Function<BigDecimal, BigDecimal> result = calculateMap.get(memberType);
            orderEntity.setPoints(result!=null ? result.apply(orderVO.getAmount()) : new BigDecimal(0));

            try {
                return orderRepository.save(orderEntity);
            } catch (Exception e) {
                String errorMsg = String.format("error occurs adding order for req:[%s]",new Gson().toJson(orderVO));
                logger.error(errorMsg,e);
                throw new AppException(StatusCodeConstant.SERVER_INTERNAL_ERROR,errorMsg);
            }
        }else {
            String errorMsg = String.format("could not find person from personId:[%s]", personId);
            logger.error(errorMsg);
            throw new AppException(StatusCodeConstant.BAD_REQUEST, errorMsg);
        }

    }

    public OrderEntity findOrderById(Long orderId){
        Optional<OrderEntity> optionalOrderEntity =  orderRepository.findById(orderId);
        return optionalOrderEntity.isPresent() ? optionalOrderEntity.get() : null;
    }
}
