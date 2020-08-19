package com.example.idemcompoent.config;

import com.example.idemcompoent.exception.TokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理
@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(TokenException.class)
    public String idemException(TokenException e){
        return e.getMessage();
    }
}
