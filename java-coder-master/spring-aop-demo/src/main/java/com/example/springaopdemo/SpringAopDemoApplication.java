package com.example.springaopdemo;

import com.example.springaopdemo.component.TargetClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringAopDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context  = SpringApplication.run(SpringAopDemoApplication.class, args);
        TargetClass targetClass = context.getBean(TargetClass.class);
        targetClass.test("");
    }

}
