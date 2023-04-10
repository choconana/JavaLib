package com.example.springbootmybatisplusdemo;

import com.example.springbootmybatisplusdemo.service.IMultDruidService;
import com.example.springbootmybatisplusdemo.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootMybatisPlusDemoApplicationTests {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMultDruidService multDruidService;

    @Test
    void contextLoads() {
        System.out.println(userService.list());
    }

    //    测试动态数据源
    @Test
    void mult_test() {
        System.out.println(multDruidService.findInMaster());
        System.out.println(multDruidService.findInSalve());
    }


}
