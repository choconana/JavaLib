package com.example.springboot01config;

import com.example.springboot01config.bean.ConfigBean;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ConfigBean.class})
public class SpringbootConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootConfigApplication.class, args);
    }

}
