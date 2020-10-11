package com.demo.ydyp.demo.service.impl;


import com.demo.ydyp.demo.common.redis.RedisService;
import com.demo.ydyp.demo.common.redis.impl.RedisUtil;
import com.demo.ydyp.demo.dto.TokenDto;
import com.demo.ydyp.demo.entity.UserEntity;
import com.demo.ydyp.demo.mapper.UserMapper;
import com.demo.ydyp.demo.service.UserService;
import com.demo.ydyp.demo.util.Md5Util;
import com.demo.ydyp.demo.util.RedisSpecificFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

/**
 * @author HaiPeng Wang
 * @date 2020/9/27 16:58
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisSpecificFunction redisSpecificFunction;

    @Autowired
    Md5Util md5Util;

    @Autowired
    RedisSpecificFunction specificFunction;

    @Autowired
    RedisService redisService;

    @Override
    public List<UserEntity> getAllUser() {
        List<UserEntity> userEntityList = userMapper.getAllUser();
        System.out.println(userEntityList);
        return userEntityList;
    }

    @Override
    public Integer registerByPhone(String phone, String password, String phone_code,String image_code,String user_ip) {
        String redis_imageCode =(String)redisUtil.get(user_ip);
        String phone_tempCode =(String)redisUtil.get(phone);
        List<UserEntity> userEntityList = (List<UserEntity>) redisUtil.get("USERLIST");
        if (userEntityList == null){
            redisSpecificFunction.setUserEntityList();
        }
        int en = 0;
        /**
         * 1 ---> 注册成功
         * 0 ---> 用户已存在
         * 2 ---> 图形验证码错误
         * 3 ---> 手机验证码错误
         * 4 ---> 未存入redis
         */
        int result = 1;
        for (UserEntity userEntity : userEntityList){
            if (userEntity.getUser_phone().equals(phone)){
                en=0;
            }else {
                en=1;
            }
        }
        if (en==0){
            if (!redis_imageCode.equals(null)||!phone_tempCode.equals(null)){
                if(phone_tempCode.equals(phone_code)){
                    if (redis_imageCode.equals(image_code)){
                        UserEntity userEntity =new UserEntity();
                        userEntity.setUser_id(getUUID());
                        userEntity.setUser_phone(phone);
                        try {
                            userEntity.setUser_password(md5Util.encryption(password));
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        userEntity.setUser_name("云牍小客"+userEntity.getUser_id());
                        userMapper.insertUser(userEntity);
                        result = 1;
                        return 1;
                    }else {
                        result =4;
                        return 4;
                    }
                }else {
                    //手机验证码不正确
                    result = 3;
                    return 3;
                }
            }else {
                //未存入
                System.out.println(redis_imageCode+":::"+phone_tempCode);
                System.out.println("/service/impl/UserService/registerByphone:未存入redis");
                result = 5;
                return result;
            }
        }else {
            //用户已存在
            result = 0;
            return result;
        }


    }

    /**
     * 通过手机号和密码进行登录
     *
     * @param user_phone
     * @param user_password
     * @return
     */
    @Override
    public int loginByPhoneAndPassword(String user_phone, String user_password) throws NoSuchAlgorithmException {
        List<UserEntity> userEntityList = (List<UserEntity>) redisUtil.get("USERLIST");
        int result = 0;
        /**
         * 1 ---> 登录成功
         * 0 ---> 用户已存在
         * 2 ---> 没有该用户
         * 3 ---> 密码不正确
         * 4 ---> 未存入redis
         */
        // TODO: 加强从遍历数据库到遍历redis,逻辑不明确
        if (userEntityList != null){
            for (int i = 0 ; i <userEntityList.size() ; i ++){
                if (user_phone.equals(userEntityList.get(i).getUser_phone())){
                    //redis中存着这个用户数据
                    UserEntity userEntity = userEntityList.get(i);
                    String md5Password = md5Util.encryption(user_password);
                    if (userEntity.getUser_password().equals(md5Password)){
                        //密码正确,添加token，并设置两个小时的过期时间
                        redisService.add(userEntity.getUser_id());
                        //登录成功
                        return 1;
                    }else {
                        //密码不正确
                        return 3;
                    }

                }else {
                    UserEntity userEntity = userMapper.selectByPhone(user_phone);
                    if (userEntity == null){
                        //2）情况二这个人还没有注册
                        return 2;
                    }else {
                        //1)情况一数据库中有这个数据
                        String md5Password = md5Util.encryption(user_password);
                        if (userEntity.getUser_password().equals(md5Password)){
                            //密码正确,添加token，并设置两个小时的过期时间
                            redisService.add(userEntity.getUser_id());
                            //登录成功
                            return 1;
                        }else {
                            //密码不正确
                            return 3;
                        }
                    }


                }
            }
        }else {
            return  4;
        }
        return 0;
    }

    /**
     * 通过手机号和验证码进行登录
     *
     * @param user_phone
     * @param phoneCode
     * @return
     */
    @Override
    public TokenDto loginByPhoneAndCode(String user_phone, String phoneCode) {
        //获取redis中存入的验证码信息
        String redisPhone_code = (String) redisUtil.get(user_phone);
        /**
         * 1 ---> 登录成功
         * 3 ---> 验证码不正确
         * 4 ---> 验证码已过期
         */

        if (redisPhone_code == null){
            //redis 中存入的内容已过期
            return new TokenDto(4,null);
        }else {
            if (redisPhone_code.equals(phoneCode)){
                //验证码匹配成功
                //登录成功
                //添加token，并设置两个小时的过期时间
                UserEntity userEntity = userMapper.selectByPhone(user_phone);
                String token = redisService.add(userEntity.getUser_id());
                return new TokenDto(1,token);
            }else {
                //验证码不正确
                return new TokenDto(3,null);
            }
        }
    }


    /**
     * 内置用于获取UUID的方法（不含-）
     * @return
     */
    private String getUUID(){
        String value = UUID.randomUUID().toString();
        return value.replaceAll("-","");
    }

    /**
     * 类方法获取UserEntityList
     */
    static List<UserEntity> getAll(){
        UserServiceImpl userService = new UserServiceImpl();
        return userService.getAllUser();
    }
}
