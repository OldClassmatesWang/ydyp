package com.demo.ydyp.demo.common.redis.impl;

import com.demo.ydyp.demo.common.redis.RedisService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author HaiPeng Wang
 * @date 2020/10/4 15:18
 * @Description:
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    final static String LOGINTOKEN = "LOGIN-TOKEN:";

    final static String TIMETOKEN = "TIME:";

    final static String USERID= "USER-ID:";
    /**
     * 1）向redis中添加token信息
     * token名： LOGIN-TOKEN
     * Key数据类型 ：String-----Token
     * value数据类型：UUID
     * 携带内容：
     * 用于登录的token
     * <p>
     * token名：TIME-TOKEN
     * key数据类型：String----UUID
     * value数据类型：Date
     * 携带内容：
     * 缓存登录的时间
     *
     *
     */
    @Override
    public void add(String user_id) {
        String key = getUUID();
        String value = user_id;
        String keyTime = user_id;
        String valueTime = new Date().toString();
        //设置 key（uuid）。value（USER-ID：userid）
        redisTemplate.opsForValue().set(key,USERID+value);
        //设置 key（userid） value（TIME：loginTime.toString）
        redisTemplate.opsForValue().set(keyTime,TIMETOKEN+valueTime);
    }

    /**
     * 2）从redis中根据token查询userId
     *
     * @param LOGINTOKEN
     */
    @Override
    public String getUserID(String LOGINTOKEN) {
        String loginToken = LOGINTOKEN;
        String userId = redisTemplate.opsForValue().get(loginToken);
        if (userId == null){
            return null;
        }else {
            return userId.substring(8,userId.length());
        }


    }

    /**
     * 3)从redis中根据userId查询登录时间
     *
     * @param USERID
     */
    @Override
    public Date getLoginTime(String USERID) throws ParseException {
        String userId = USERID;
        String loginTime = redisTemplate.opsForValue().get(userId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(loginTime);
        return date;
    }

    /**
     * 内置用于获取UUID的方法（不含-）
     * @return
     */
    private String getUUID(){
        String value = UUID.randomUUID().toString();
        return value.replaceAll("-","");
    }
}
