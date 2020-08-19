package com.example.idemcompoent.interceptor;

import com.example.idemcompoent.annotation.IdemAnnotation;
import com.example.idemcompoent.exception.TokenException;
import com.example.idemcompoent.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Wangts
 */
@Component
public class IdemInterceptor implements HandlerInterceptor {

    @Autowired
    TokenService tokenService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //先判断这次handler是不是自定义注解方法handler，因为自定义注解的IdemAnnotion是作用于方法上
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        IdemAnnotation idemAnnotation = method.getAnnotation(IdemAnnotation.class);
        //对注解方法进行判断
        if (idemAnnotation !=null){
            //如果是幂等注解
            try {
                return tokenService.checkTokenExsits(request);
            } catch (TokenException e) {
                e.printStackTrace();
                //可以用全局异常处理去接受处理
                throw e;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
