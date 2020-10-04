package com.demo.ydyp.demo.service.impl;


import com.demo.ydyp.demo.common.redis.impl.RedisUtil;
import com.demo.ydyp.demo.entity.UserEntity;
import com.demo.ydyp.demo.mapper.UserMapper;
import com.demo.ydyp.demo.service.UserService;
import com.demo.ydyp.demo.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

/**
 * @author HaiPeng Wang
 * @date 2020/9/27 16:58
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    Md5Util md5Util;

    @Override
    public List<UserEntity> getAllUser() {
        List<UserEntity> userEntityList = userMapper.getAllUser();
        System.out.println(userEntityList);
        return userEntityList;
    }

    @Override
    public boolean registerByPhone(String phone, String password, String code,String user_ip) {
        String redis_imageCode =(String)redisUtil.get(user_ip);
        if (redis_imageCode.equals(code)){
            UserEntity userEntity =new UserEntity();
            userEntity.setUser_id(getUUID());
            userEntity.setUser_phone(phone);
            try {
                userEntity.setUser_password(md5Util.encryption(password));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            userEntity.setUser_name("云牍小客"+userEntity.getUser_id());
            userMapper.insertUser(userEntity);
            return true;
        }else {
            //图形验证码不正确
            return false;
        }
    }

    /**
     * 内置用于获取UUID的方法（不含-）
     * @return
     */
    private String getUUID(){
        String value = UUID.randomUUID().toString();
        return value.replaceAll("-","");
    }
}
