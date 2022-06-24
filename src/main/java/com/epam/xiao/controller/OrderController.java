package com.epam.xiao.controller;


import com.epam.xiao.constants.StatusCodeConstant;
import com.epam.xiao.entity.OrderEntity;
import com.epam.xiao.exception.AppException;
import com.epam.xiao.exception.ResponseVO;
import com.epam.xiao.service.OrderService;
import com.epam.xiao.vo.OrderVO;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/add")
    public ResponseVO<OrderEntity> addOrder(@Valid @RequestBody OrderVO orderVO){
        logger.debug("start to add order for req:[{}]",new Gson().toJson(orderVO));

        OrderEntity res = orderService.addOrder(orderVO);

        return ResponseVO.ok(res);

    }

    @PostMapping(value = "/query")
    public ResponseVO<OrderEntity> findOrder(@RequestParam(value = "orderID") Long orderId){
          logger.debug("start to find Order by id:[{}]", orderId);

          OrderEntity res = null;

        try {
            res = orderService.findOrderById(orderId);
        } catch (Exception e) {
            String errorMsg = String.format("error occurs find order for orderId:[%s]",orderId);
            logger.error(errorMsg,e);
            throw new AppException(StatusCodeConstant.SERVER_INTERNAL_ERROR,errorMsg);
        }

        logger.debug("success find order by id:[{}], res:[{}]", orderId, new Gson().toJson(res));

       return ResponseVO.ok(res);
    }
}
