package com.demo.ydyp.demo.common.redis.impl;

/**
 * @author HaiPeng Wang
 * @date 2020/10/4 17:42
 * @Description:
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;


/**
 * 操作redis的工具类
 */
@Component
public class RedisUtil {

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    /**
     * 用于向redis中存入Object数据
     */
    public void set(Object key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 用于从redis中取出Object数据
     */
    public Object get(Object key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 用于向redis中存入Object数据并设置过期时长
     */
    public void set(Object key,Object value, long time){
        redisTemplate.opsForValue().set(key,value);
        Duration duration = Duration.ofSeconds(time);
        redisTemplate.expire(key,duration);
    }

}
