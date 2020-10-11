package com.demo.ydyp.demo.mapper;

import com.demo.ydyp.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HaiPeng Wang
 * @date 2020/9/27 17:46
 * @Description:
 */
@Repository
public interface UserMapper {

    @Select("SELECT * FROM user;\n")
    List<UserEntity> getAllUser();


    @Insert("INSERT INTO `user`SET `user`.user_id = #{user_id}, `user`.user_name = #{user_name},`user`.user_email = #{user_email},`user`.user_password= #{user_password},`user`.user_phone = #{user_phone}")
    void insertUser(UserEntity userEntity);

    @Select("SELECT * FROM user WHERE user_phone = #{user_phone}")
    UserEntity selectByPhone(String user_phone);
}
