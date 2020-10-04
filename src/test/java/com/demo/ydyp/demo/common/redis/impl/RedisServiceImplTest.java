package com.demo.ydyp.demo.common.redis.impl;

import com.demo.ydyp.demo.common.redis.RedisService;
import org.apache.ibatis.annotations.Insert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author HaiPeng Wang
 * @date 2020/10/4 15:54
 * @Description:
 */
@SpringBootTest
@Service
class RedisServiceImplTest {
    @Autowired
    RedisService redisService;

    @Test
    public void test1(){
        String userid= "123456";
        redisService.add(userid);
    }

    @Test
    public  void test2(){
        System.out.println(redisService.getUserID("8fe1323f673848c88befeab8e9b5f1d6"));
    }
}