package com.demo.ydyp.demo.controller;

import com.demo.ydyp.demo.common.redis.impl.RedisUtil;
import com.demo.ydyp.demo.common.wrapper.ReturnWrapper;
import com.demo.ydyp.demo.form.UserIpForm;
import com.demo.ydyp.demo.form.UserPhoneForm;
import com.demo.ydyp.demo.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author HaiPeng Wang
 * @date 2020/10/4 16:19
 * @Description:
 */

/**
 * 用于获取图形验证码，手机号验证码，邮箱验证码的接口类
 */
@RequestMapping("/code")
@Controller
public class CodeController {

    @Autowired
    CodeService codeService;

    @Autowired
    RedisUtil redisUtil;


    /**
     * 获取图形验证码图片返回给前端
     * @throws IOException
     */
    @RequestMapping(path = "/image", produces = MediaType.IMAGE_PNG_VALUE,method = RequestMethod.POST)
    public void getImageCode(@RequestBody UserIpForm userIpForm, HttpServletResponse response){
        try {
            BufferedImage image = codeService.getImageCode(userIpForm.getUser_ip());
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image,"png",out);
        }catch (IOException e){
        }
    }

    /**
     * 获取手机号验证码
     */
    @ResponseBody
    @RequestMapping(path = "/phone",method = RequestMethod.POST)
    public ReturnWrapper getPhoneCode(@RequestBody UserPhoneForm userPhoneForm){
        if(codeService.getPhoneCode(userPhoneForm.user_phone)){
            return new ReturnWrapper().success();
        }else {
            return new ReturnWrapper().failure();
        }
    }
}
