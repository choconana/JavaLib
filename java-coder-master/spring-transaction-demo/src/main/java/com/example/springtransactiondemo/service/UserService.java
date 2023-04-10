package com.example.springtransactiondemo.service;

import com.example.springtransactiondemo.entity.User;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2022年05月03日 17:19:00
 */
public interface UserService {

    void saveUserTest(User user) throws Exception;

    void saveUser(User user) throws Exception;

}