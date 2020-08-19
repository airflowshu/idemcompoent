package com.example.idemcompoent.token;

import com.example.idemcompoent.exception.TokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Component
public class TokenService {

    @Autowired
    RedisService redisService;

    /** 
    * @Description: com.example.idemcompoent.token
    * @Param:  
    * @return:  
    * @Author: Wangts 
    * @create: 2020-08-19 14:33
    */
    public String createToken(){
        String s = UUID.randomUUID().toString();
        redisService.setEx(s,s,100L);
        return s;
    }

    
    public boolean checkTokenExsits(HttpServletRequest request) throws TokenException {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)){
            if (StringUtils.isEmpty(request.getParameter("token"))){
                throw new TokenException("token不存在");
            }
        }

        if (!redisService.exsits(token)) {
            //可能被移除了，抛出为重复的操作
            throw new TokenException("重复的操作");
        }

        //上述都没抛出，则认为是第一次请求
        return redisService.remove(token);

    }
}
