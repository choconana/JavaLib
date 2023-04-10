package com.example.springtransactiondemo.service.imple;

import com.example.springtransactiondemo.entity.User;
import com.example.springtransactiondemo.exception.ParamInvalidException;
import com.example.springtransactiondemo.mapper.UserMapper;
import com.example.springtransactiondemo.service.UserService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2022年05月03日 17:19:00
 */
@Service
public class UserServiceImpl implements UserService, ApplicationContextAware {

    private final UserMapper userMapper;
    private ApplicationContext context;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void saveUserTest(User user) throws Exception {
//        UserService userService = context.getBean(UserService.class);
//        userService.saveUser(user);
        //AOP代理
        UserService userService = (UserService) AopContext.currentProxy();
        userService.saveUser(user);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userMapper.save(user);
        // 测试事务回滚
        if (!StringUtils.hasText(user.getUsername())) {
            throw new ParamInvalidException("username不能为空");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}