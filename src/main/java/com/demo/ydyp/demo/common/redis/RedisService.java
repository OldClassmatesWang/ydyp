package com.demo.ydyp.demo.common.redis;

import java.text.ParseException;
import java.util.Date;

/**
 * @author HaiPeng Wang
 * @date 2020/10/4 15:17
 * @Description:
 */
public interface RedisService {

    /**
     * 1）向redis中添加token信息
     * token名： LOGIN-TOKEN
     * Key数据类型 ：String-----Token
     * value数据类型：UUID
     * 携带内容：
     *  用于登录的token
     *
     *  token名：TIME-TOKEN
     *  key数据类型：String----UUID
     *  value数据类型：Date
     *  携带内容：
     *   缓存登录的时间
     */

    public String add(String user_id);

    /**
     * 2）从redis中根据token查询userId
     */
    public String getUserID(String LOGINTOKEN);


    /**
     * 3)从redis中根据userId查询登录时间
     */
    public Date getLoginTime(String USERID) throws ParseException;



}
