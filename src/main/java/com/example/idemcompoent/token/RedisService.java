package com.example.idemcompoent.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    public boolean setEx(String key,Object value,Long expireTime){

        boolean result = false;
        try {
            ValueOperations ops = redisTemplate.opsForValue();
            ops.set(key,value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result =true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    };


    public boolean exsits(String key){
        return redisTemplate.hasKey(key);

    }

    public boolean remove(String key){
        if(exsits(key)){
            return redisTemplate.delete(key);
        }
        return false;
    }
}
