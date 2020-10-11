package com.demo.ydyp.demo.service;

import com.demo.ydyp.demo.dto.TokenDto;
import com.demo.ydyp.demo.entity.UserEntity;
import com.demo.ydyp.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author HaiPeng Wang
 * @date 2020/9/27 16:54
 * @Description:
 */
@Component("userService")
public interface UserService {

    List<UserEntity> getAllUser();


    Integer registerByPhone(String phone, String password, String phone_code,String image_code,String user_ip);

    /**
     * 通过手机号和密码进行登录
     * @return
     */
    int loginByPhoneAndPassword(String user_phone,String user_password) throws NoSuchAlgorithmException;

    /**
     * 通过手机号和验证码进行登录
     * @param user_phone
     * @param phoneCode
     * @return
     */
    TokenDto loginByPhoneAndCode(String user_phone, String phoneCode);
}
