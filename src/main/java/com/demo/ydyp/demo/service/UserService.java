package com.demo.ydyp.demo.service;

import com.demo.ydyp.demo.entity.UserEntity;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * @author HaiPeng Wang
 * @date 2020/9/27 16:54
 * @Description:
 */
@Component("userService")
public interface UserService {

    List<UserEntity> getAllUser();

    boolean registerByPhone(String phone, String password, String code,String user_ip);
}
