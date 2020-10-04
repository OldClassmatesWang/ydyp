package com.demo.ydyp.demo.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author HaiPeng Wang
 * @date 2020/10/4 19:57
 * @Description:
 */
@Data
public class UserIpForm {

    @NotBlank(message = "用户ip不能为空")
    private String user_ip;
}
