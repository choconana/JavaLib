package com.example.springtransactiondemo;

import com.example.springtransactiondemo.entity.User;
import com.example.springtransactiondemo.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableTransactionManagement
public class SpringTransactionDemoApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(SpringTransactionDemoApplication.class, args);
        UserService userService = context.getBean(UserService.class);
        User user = new User("5", null, "18");
        userService.saveUserTest(user);
    }

}
