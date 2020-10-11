package com.demo.ydyp.demo.controller;

import com.demo.ydyp.demo.common.wrapper.ReturnWrapper;
import com.demo.ydyp.demo.dto.TokenDto;
import com.demo.ydyp.demo.form.LoginPhoneCoForm;
import com.demo.ydyp.demo.form.LoginPhonePsForm;
import com.demo.ydyp.demo.service.UserService;
import com.demo.ydyp.demo.util.UserUtil;
import io.lettuce.core.cluster.PubSubClusterEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

/**
 * @author HaiPeng Wang
 * @date 2020/10/4 16:12
 * @Description:
 */
@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    UserUtil userUtil;
    /**
     * 用于 手机号和密码登录
     * @return
     */
    @RequestMapping(path = "/phonePs",method = RequestMethod.POST)
    public ReturnWrapper loginByPhoneAndPassword(@RequestBody LoginPhonePsForm loginPhonePsForm) throws NoSuchAlgorithmException {
        int result = userService.loginByPhoneAndPassword(loginPhonePsForm.getUser_phone(),loginPhonePsForm.getUser_password());
        return userUtil.getResultMessageForLoginByphonePs(result);
    }


    /**
     * 用于 手机号和验证码登录
     */
    @RequestMapping(path = "/phoneCo",method = RequestMethod.POST)
    public ReturnWrapper loginByPhoneAndCode(@RequestBody LoginPhoneCoForm loginPhoneCoForm){
        TokenDto tokenDto = userService.loginByPhoneAndCode(loginPhoneCoForm.getUser_phone(),loginPhoneCoForm.getPhoneCode());
        return  userUtil.getResultMessageForLoginByphoneCo(tokenDto);
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
