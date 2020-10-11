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
        /**
         * Md5加密本质：接收任意长度大小的数据，输出固定长度的哈希值
         */
        String result = "";

        //1）MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法
        MessageDigest md = MessageDigest.getInstance("MD5");

        //2）执行加密操作
        byte[] bytes = password.getBytes();

        //3）MD5算法得到目标的特点，长度为
        byte[] md5PasswordBytes = md.digest(bytes);

        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < md5PasswordBytes.length; offset++) {
            i = md5PasswordBytes[offset];
            if (i < 0){
                i += 256;
            }
            if (i < 16){
                buf.append("0");
            }

            buf.append(Integer.toHexString(i));
        }
        result = buf.toString();
        result = result.substring(8,24);
        return result;
    }
}
