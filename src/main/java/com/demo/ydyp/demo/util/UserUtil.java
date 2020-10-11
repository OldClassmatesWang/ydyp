package com.demo.ydyp.demo.util;

import com.demo.ydyp.demo.common.wrapper.ReturnWrapper;
import com.demo.ydyp.demo.dto.TokenDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HaiPeng Wang
 * @date 2020/10/8 20:30
 * @Description:
 */
@Component
public class UserUtil {

    public ReturnWrapper getResultMessage(Integer result){
        /**
         * 1 ---> 注册成功
         * 0 ---> 用户已存在
         * 2 ---> 图形验证码错误
         * 3 ---> 手机验证码错误
         * 4 ---> 未存入redis
         */
        switch (result){
            case 0: return new ReturnWrapper().failureWithMessage("用户已存在");
            case 1: return new ReturnWrapper().successWithMessage("注册成功");
            case 2: return new ReturnWrapper().failureWithMessage("图形验证码错误");
            case 3: return new ReturnWrapper().failureWithMessage("手机验证码错误");
            case 4: return new ReturnWrapper().failureWithMessage("未存入redis");
            default: return new ReturnWrapper().failureWithMessage("错误，请联系管理员");
        }
    }

    public ReturnWrapper getResultMessageForLoginByphonePs(Integer result){
        /**
         * 1 ---> 登录成功
         * 0 ---> 用户已存在
         * 2 ---> 没有该用户
         * 3 ---> 密码不正确
         * 4 ---> 未存入redis
         */
        switch (result){
            case 0: return new ReturnWrapper().failureWithMessage("用户已存在");
            case 1: return new ReturnWrapper().successWithMessage("登录成功");
            case 2: return new ReturnWrapper().failureWithMessage("没有该用户");
            case 3: return new ReturnWrapper().failureWithMessage("密码不正确");
            case 4: return new ReturnWrapper().failureWithMessage("未存入redis");
            default: return new ReturnWrapper().failureWithMessage("错误，请联系管理员");
        }

    }

    public ReturnWrapper getResultMessageForLoginByphoneCo(TokenDto tokenDto) {
        /**
         * 1 ---> 登录成功
         * 3 ---> 验证码不正确
         * 4 ---> 验证码已过期
         */
        int result = tokenDto.getCode();
        switch (result){
            case 1: return new ReturnWrapper().successWithMessage(tokenDto.getToken());
            case 3: return new ReturnWrapper().failureWithMessage("验证码不正确");
            case 4: return new ReturnWrapper().failureWithMessage("验证码已过期");
            default: return new ReturnWrapper().failureWithMessage("错误，请联系管理员");
        }
    }
}
