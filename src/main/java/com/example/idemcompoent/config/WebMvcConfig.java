package com.example.idemcompoent.config;

import com.example.idemcompoent.interceptor.IdemInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Component
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    IdemInterceptor idemInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(idemInterceptor).addPathPatterns("/**");
    }
}
