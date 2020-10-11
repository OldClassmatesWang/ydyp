package com.demo.ydyp.demo.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * @author HaiPeng Wang
 * @date 2020/10/5 19:51
 * @Description:
 */
@Data
public class UserPhoneForm {

    @NotBlank(message = "手机号不能为空")
    @Pattern(message = "请输入正确的手机号", regexp = "^1[3|4|5|7|8][0-9]\\d{8,11}$")
    public String user_phone;
}
