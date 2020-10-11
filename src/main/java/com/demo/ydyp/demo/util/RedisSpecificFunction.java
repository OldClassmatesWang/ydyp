package com.demo.ydyp.demo.util;

import com.demo.ydyp.demo.common.redis.impl.RedisUtil;
import com.demo.ydyp.demo.entity.UserEntity;
import com.demo.ydyp.demo.service.UserService;
import com.demo.ydyp.demo.service.impl.UserServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HaiPeng Wang
 * @date 2020/10/8 20:06
 * @Description:
 */
@Component
@Data
public class RedisSpecificFunction {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserService userService;

    /**
     * 每次启动就把UserEntityList更新一次
     */
    public  void setUserEntityList(){
        List<UserEntity> userEntityList = userService.getAllUser();
        redisUtil.set("USERLIST",userEntityList);
    }
}
