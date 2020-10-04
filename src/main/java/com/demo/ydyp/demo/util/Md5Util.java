package com.demo.ydyp.demo.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author HaiPeng Wang
 * @date 2020/10/4 18:17
 * @Description:
 */
@Component
public class Md5Util {

    /**
     * 将对应的信息进行加密
     */
    public String encryption(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(password.getBytes());
        String md5Password = new String(bytes);
        return md5Password;
    }
}
