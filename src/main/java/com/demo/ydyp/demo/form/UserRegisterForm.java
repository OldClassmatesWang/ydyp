package com.demo.ydyp.demo.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * @author HaiPeng Wang
 * @date 2020/10/4 17:14
 * @Description:
 */
@Data
public class UserRegisterForm {

    @NotBlank(message = "手机号不能为空")
    @Pattern(message = "请输入正确的手机号", regexp = "^1[3|4|5|7|8][0-9]\\d{8,11}$")
    private String phone;

    @NotBlank(message =  "图形验证码不能为空")
    private String image_code;

    @NotBlank(message = "手机号验证码不能为空")
    private String phone_code;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "用户ip不能为空")
    private String user_ip;
}
