package com.demo.ydyp.demo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author HaiPeng Wang
 * @date 2020/9/27 16:43
 * @Description:
 */
@Data
public class UserdataEntity {

    private String userId;

    private String noteId;

    private String noteHeadline;

    private String noteContent;

    private int notePublic;

    private int noteVerify;

    private String noteLabel;

    private String noteShowpic;

    private Date noteCreatetime;

    private Date noteUpdatetime;

}
