package com.example.springdemo;

import com.example.springdemo.config.WebConfig;
import com.example.springdemo.entity.User;
import com.example.springdemo.entity.UserBean;
import com.example.springdemo.service.CalculateService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import java.util.Arrays;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);

        // 返回 IOC 容器，使用注解配置，传入配置类
//        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
//        System.out.println("容器创建完毕");
//
//        User user1 = context.getBean(User.class);
//        User user2 = context.getBean(User.class);
//        System.out.println(user1 == user2);
//        // 查看 User 这个类在 Spring 容器中叫啥玩意
//        String[] beanNames = context.getBeanNamesForType(User.class);
//        Arrays.stream(beanNames).forEach(System.out::println);
        // 查看基于注解的 IOC容器中所有组件名称
//        String[] beanNames = context.getBeanDefinitionNames();
//        Arrays.stream(beanNames).forEach(System.out::println);
//        Object cherry = context.getBean("cherryFactoryBean");
//        System.out.println(cherry.getClass());
//        Object cherryFactoryBean = context.getBean("&cherryFactoryBean");
//        System.out.println(cherryFactoryBean.getClass());

        // 返回 IOC 容器，使用注解配置，传入配置类
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
////        UserBean bean = context.getBean(UserBean.class);
//        // 关闭 IOC 容器
//        context.close();

    }

}
