package com.demo.ydyp.demo.service.impl;

import com.demo.ydyp.demo.common.redis.impl.RedisUtil;
import com.demo.ydyp.demo.service.CodeService;
import com.demo.ydyp.demo.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author HaiPeng Wang
 * @date 2020/10/4 19:41
 * @Description:
 */
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ImageCodeUtil codeUtil;

    /**
     * 获取 图形验证码
     * @param userIp
     * @return
     */
    @Override
    public BufferedImage getImageCode(String userIp) {
        BufferedImage image = codeUtil.getCodeImage(userIp);
        return image;
    }

    /**
     * 获取 手机号验证码
     * @param user_phone
     * @return
     */
    @Override
    public boolean getPhoneCode(String user_phone) {

        try {
            String postUrl = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

            Integer mobile_code = (int) ((Math.random() * 9 + 1) * 100000); //获取随机数
            redisUtil.set(user_phone, mobile_code.toString(),60*10);

            String mobile = user_phone;
            String account = "C05973403"; //查看用户名是登录用户中心->验证码短信->产品总览->APIID
            String password = "acaf89814b586c185ecddc173386ca62";  //查看密码请登录用户中心->验证码短信->产品总览->APIKEY
            String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");//发送给手机的信息

            URL url = new URL(postUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);//允许连接提交信息
            StringBuffer sb = new StringBuffer();
            sb.append("account=" + account);
            sb.append("&password=" + password);
            sb.append("&mobile=" + mobile);
            sb.append("&content=" + content);
            OutputStream os = connection.getOutputStream();
            os.write(sb.toString().getBytes());
            os.close();

            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }
}
