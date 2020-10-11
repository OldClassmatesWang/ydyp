package com.demo.ydyp.demo.controller;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement;
import com.demo.ydyp.demo.common.wrapper.ReturnWrapper;
import com.demo.ydyp.demo.form.UserRegisterForm;
import com.demo.ydyp.demo.service.UserService;
import com.demo.ydyp.demo.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author HaiPeng Wang
 * @date 2020/10/4 16:13
 * @Description:
 */

/**
 * 用于注册的接口类
 */
@RequestMapping("/register")
@RestController
public class RegisterController {
    @Autowired
    UserService userService;

    @Autowired
    UserUtil userUtil;

    /**
     * 根据 手机号 注册
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/phone",method = RequestMethod.POST)
    public ReturnWrapper registerByPhone(@RequestBody UserRegisterForm userRegisterForm){
//        if (userService.registerByPhone(userRegisterForm.getPhone(),
//                userRegisterForm.getPassword(),
//                userRegisterForm.getPhone_code(),
//                userRegisterForm.getImage_code(),
//                userRegisterForm.getUser_ip())){
//            return new ReturnWrapper().success();
//        }else{
//            return new ReturnWrapper().failure();
//        }
        Integer result  = userService.registerByPhone(userRegisterForm.getPhone(),
                userRegisterForm.getPassword(),
                userRegisterForm.getPhone_code(),
                userRegisterForm.getImage_code(),
                userRegisterForm.getUser_ip());

        return userUtil.getResultMessage(result);

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
