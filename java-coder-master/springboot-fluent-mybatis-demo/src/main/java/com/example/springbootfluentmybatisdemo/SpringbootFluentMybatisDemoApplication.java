package com.example.springbootfluentmybatisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springbootfluentmybatisdemo")
public class SpringbootFluentMybatisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootFluentMybatisDemoApplication.class, args);
    }

}
