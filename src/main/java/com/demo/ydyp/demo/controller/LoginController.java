package com.demo.ydyp.demo.controller;

import com.demo.ydyp.demo.common.wrapper.ReturnWrapper;
import com.demo.ydyp.demo.service.UserService;
import io.lettuce.core.cluster.PubSubClusterEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author HaiPeng Wang
 * @date 2020/10/4 16:12
 * @Description:
 */
@RequestMapping("/login")
@Controller
public class LoginController {

    @Autowired
    UserService userService;
    /**
     * 用于 手机号和密码登录
     * @return
     */
    public ReturnWrapper loginByPhoneAndPassword(){

        return new ReturnWrapper();
    }

    /**
     * 用于 手机号和验证码登录
     */
    public ReturnWrapper loginByPhoneAndCode(){

        return  new ReturnWrapper();
    }

    /**
     * 用于 邮箱和密码登录
     */
    public ReturnWrapper loginByEmailAndPassword(){
        return new ReturnWrapper();
    }

    /**
     * 用于 邮箱和验证码登录
     */
    public ReturnWrapper loginByEmailAndCode(){

        return new ReturnWrapper();
    }

    /**
     * 用于 用户名和密码登录
     */
    public ReturnWrapper loginByNameAndPassword(){

        return  new ReturnWrapper();
    }

}
