package com.demo.ydyp.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author HaiPeng Wang
 * @date 2020/9/27 16:03
 * @Description:
 */

@Data
public class UserEntity implements Serializable {

    private String user_id;

    private String user_name;

    private String user_password;

    private String user_email;

    private String user_phone;

}
