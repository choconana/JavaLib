package com.example.springbootmybatisplusdemo.service.impl;

import com.example.springbootmybatisplusdemo.dal.model.User;
import com.example.springbootmybatisplusdemo.dal.mapper.UserMapper;
import com.example.springbootmybatisplusdemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chen
 * @since 2022-06-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
