package com.demo.ydyp.demo.dto;

import lombok.Data;

/**
 * @author HaiPeng Wang
 * @date 2020/10/11 10:27
 * @Description:
 */
@Data
public class TokenDto {

    private int code;

    private String token;

    public TokenDto(int code, String token) {
        this.code = code;
        this.token = token;
    }

    public TokenDto() {
    }
}
