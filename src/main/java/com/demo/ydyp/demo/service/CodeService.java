package com.demo.ydyp.demo.service;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;

/**
 * @author HaiPeng Wang
 * @date 2020/10/4 19:41
 * @Description:
 */
@Component("codeService")
public interface CodeService {

    /**
     * 获取图形验证码
     * @param userIp
     * @return
     */
    BufferedImage getImageCode(String userIp);

    /**
     * 获取手机号验证码
     * @param user_phone
     * @return
     */
    boolean getPhoneCode(String user_phone);
}
