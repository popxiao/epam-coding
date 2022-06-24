package com.epam.xiao.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppException extends RuntimeException{

    private String errorCode;

    private String errorMsg;

    private Object[] value;

    public AppException(String errorCode, String errorMsg, Object ... value){
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.value = value;
    }
}
