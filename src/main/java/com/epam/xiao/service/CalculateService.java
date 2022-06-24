package com.epam.xiao.service;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculateService {

    public BigDecimal getGoldPoints(BigDecimal amount){
        return amount.multiply(new BigDecimal(3));
    }

    public BigDecimal getSilverPoints(BigDecimal amount){
        return amount.multiply(new BigDecimal(2));
    }

    public BigDecimal getCoperPoints(BigDecimal amount){
        return amount.multiply(new BigDecimal(1));
    }
}
