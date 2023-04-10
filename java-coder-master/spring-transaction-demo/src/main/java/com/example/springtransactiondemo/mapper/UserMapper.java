package com.example.springtransactiondemo.mapper;

import com.example.springtransactiondemo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName UserMapper.java
 * @Description TODO
 * @createTime 2022年05月03日 17:18:00
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(user_id,username,age) values(#{userId},#{username},#{age})")
    void save(User user);

}
