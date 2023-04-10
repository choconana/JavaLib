package com.example.springdemo.config;

import com.example.springdemo.entity.*;
import com.example.springdemo.filter.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName WebConfig.java
 * @Description TODO
 * @createTime 2022年05月01日 16:44:00
 */
//@Configuration
//@ComponentScan(value = {"com.example.springdemo.controller",
//        "com.example.springdemo.entity",
//        "com.example.springdemo.dao",
//        "com.example.springdemo.service"},
//        excludeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION,
//                        classes = {RestController.class}),
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = User.class)
//        })
//@ComponentScan(value = {"com.example.springdemo.controller",
//        "com.example.springdemo.entity",
//        "com.example.springdemo.dao",
//        "com.example.springdemo.service"},
//        includeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class)
//        }, useDefaultFilters = false)
//@ComponentScan(value = {"com.example.springdemo.controller",
//        "com.example.springdemo.entity",
//        "com.example.springdemo.dao",
//        "com.example.springdemo.service"},
//        excludeFilters = {
//                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyTypeFilter.class)
//        })
@Import({Hello.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class WebConfig {

    @Bean("myUser")
//        @Scope(value = "prototype")
    @Lazy
    public User user() {
        System.out.println("往IOC容器中注册user bean");
        return new User("cc", 18);
    }

    @Bean
    public CherryFactoryBean cherryFactoryBean() {
        return new CherryFactoryBean();
    }

    @Scope(value = "prototype")
    @Bean(initMethod = "init", destroyMethod = "destory")
    public UserBean userBean() {
        return new UserBean();
    }

    @Bean
    public Bird bird() {
        return new Bird();
    }

    @Bean
    public Fish fish() {
        return new Fish();
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }

    @Bean
    public MyBeanInstantiationPostProcessor myBeanInstantiationPostProcessor() {
        return new MyBeanInstantiationPostProcessor();
    }
}