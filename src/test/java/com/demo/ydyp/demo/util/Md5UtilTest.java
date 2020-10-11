package com.demo.ydyp.demo.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author HaiPeng Wang
 * @date 2020/10/11 8:30
 * @Description:
 */
@SpringBootTest
class Md5UtilTest {

    @Autowired
    Md5Util md5Util;

    @Test
    public void test() throws NoSuchAlgorithmException {
        System.out.println(md5Util.encryption("123456"));
    }
}