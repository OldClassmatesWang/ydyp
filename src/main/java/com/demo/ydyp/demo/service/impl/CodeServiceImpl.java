package com.demo.ydyp.demo.service.impl;

import com.demo.ydyp.demo.service.CodeService;
import com.demo.ydyp.demo.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

/**
 * @author HaiPeng Wang
 * @date 2020/10/4 19:41
 * @Description:
 */
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    ImageCodeUtil codeUtil;

    @Override
    public BufferedImage getImageCode(String userIp) {
        BufferedImage image =  codeUtil.getCodeImage(userIp);
        return image;
    }
}
