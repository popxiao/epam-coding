package com.epam.xiao.exception;

import com.epam.xiao.constants.StatusCodeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(AppException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseVO handleException(AppException ae){
        ResponseVO to = new ResponseVO();
        to.setCode(ae.getErrorCode() != null ? ae.getErrorCode() : StatusCodeConstant.SERVER_INTERNAL_ERROR);

        String errorMessage = ae.getErrorMsg();

        if(StringUtils.isEmpty(errorMessage)){
            errorMessage = ae.getClass().getSimpleName();
        }
        to.setMessage(errorMessage);
        logger.error("AppException code is [{}] and msg is [{}]", ae.getErrorCode(), errorMessage);
        return to;

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseVO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("MethodArgumentNotValidException error is:[{}]", e.getBindingResult().getFieldError().getDefaultMessage(),e);
        return new ResponseVO(StatusCodeConstant.BAD_REQUEST, e.getBindingResult().getFieldError().getDefaultMessage(),null);
    }

}
