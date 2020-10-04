package com.demo.ydyp.demo.impl;


import com.demo.ydyp.demo.entity.UserEntity;
import com.demo.ydyp.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author HaiPeng Wang
 * @date 2020/9/29 14:53
 * @Description:
 */

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    public void test(){
        userService.getAllUser();
    }



}