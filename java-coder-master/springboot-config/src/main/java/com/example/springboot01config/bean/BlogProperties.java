package com.example.springboot01config.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class BlogProperties {

    @Value("${cc.blog.name}")
    private String name;

    @Value("${cc.blog.title}")
    private String title;

}