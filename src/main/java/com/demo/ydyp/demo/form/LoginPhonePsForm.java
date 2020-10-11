package com.demo.ydyp.demo.form;

import lombok.Data;

/**
 * @author HaiPeng Wang
 * @date 2020/10/11 8:34
 * @Description:
 * 用于手机号登录时接收手机号和密码
 */
@Data
public class LoginPhonePsForm {


    private String user_phone;


    private String user_password;

}
