package com.example.idemcompoent.controller;

import com.example.idemcompoent.annotation.IdemAnnotation;
import com.example.idemcompoent.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloTest {

    @Autowired
    TokenService tokenService;
    @RequestMapping("/getToken")
    public String getToken(){
        return tokenService.createToken();
    }

    @RequestMapping("/hello")
    @IdemAnnotation
    public String hello(){
        return "hello";
    }

    @RequestMapping("/hello2")
    public String hello2(){
        return "hello2";
    }
}
