package com.example.springboot01config.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cc.blog")
@Data
public class ConfigBean {

    private String name;

    private String title;

}