package com.epam.xiao.exception;

import com.epam.xiao.constants.StatusCodeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO<T> implements Serializable {

    private static final long serialVersionUID = 1l;

    private String code;

    private String message;

    private T data;

    public static ResponseVO ok(Object data){
        ResponseVO responseVO = new ResponseVO();
        responseVO.code = StatusCodeConstant.SUCCESS_CODE;
        responseVO.message = "success";
        responseVO.data = data;
        return responseVO;
    }
}
