package com.demo.ydyp.demo.controller;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement;
import com.demo.ydyp.demo.common.wrapper.ReturnWrapper;
import com.demo.ydyp.demo.form.UserRegisterForm;
import com.demo.ydyp.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author HaiPeng Wang
 * @date 2020/10/4 16:13
 * @Description:
 */

/**
 * 用于注册的接口类
 */
@RequestMapping("/register")
@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    /**
     * 根据 手机号 注册
     * @return
     */
    @RequestMapping(value = "/phone",method = RequestMethod.POST)
    public ReturnWrapper registerByPhone(@RequestBody UserRegisterForm userRegisterForm){
        System.out.println("访问到了");
        if (userService.registerByPhone(userRegisterForm.getPhone(),
                userRegisterForm.getPassword(),
                userRegisterForm.getImageCode(),
                userRegisterForm.getUser_ip())){
            return new ReturnWrapper();
        }else{
            return new ReturnWrapper();
        }
    }

    /**
     * 根据 邮箱注册
     */
    public ReturnWrapper registerByEmail(){

        return new ReturnWrapper();
    }

    /**
     * 根据 用户名（内部注册）
     */
    public ReturnWrapper registerByName(){

        return  new ReturnWrapper();
    }

}
