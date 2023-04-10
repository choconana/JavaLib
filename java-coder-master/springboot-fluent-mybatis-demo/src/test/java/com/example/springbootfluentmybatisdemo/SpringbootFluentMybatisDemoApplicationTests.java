package com.example.springbootfluentmybatisdemo;

import com.example.springbootfluentmybatisdemo.dao.intf.UserInfoDao;
import com.example.springbootfluentmybatisdemo.entity.UserInfoEntity;
import com.example.springbootfluentmybatisdemo.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringbootFluentMybatisDemoApplicationTests {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    void contextLoads() {
        List<UserInfoEntity> list = userInfoMapper.listEntity(userInfoMapper.query());
        for (UserInfoEntity entity : list) {
            System.out.println(entity);
        }
    }

    @Test
    void insertBatch(){
        List<UserInfoEntity> entities = new ArrayList<>();
        entities.add(new UserInfoEntity().setName("Fluent Mybatis").setEmail("darui.wu@163.com"));
        entities.add(new UserInfoEntity().setName("Fluent Mybatis Demo").setEmail("darui.wu@163.com"));
        entities.add(new UserInfoEntity().setName("Test4J").setEmail("darui.wu@163.com"));
        int count = userInfoMapper.insertBatch(entities);
        System.out.println("count:" + count);
        System.out.println("entity:" + entities);
    }


}
