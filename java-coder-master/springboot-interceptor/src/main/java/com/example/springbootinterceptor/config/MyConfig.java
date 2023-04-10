package com.example.springbootinterceptor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册配置文件
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        String[] path = {"/user/**"}; // 如果拦截全部可以设置为 /**
        String[] excludePath = {"/user/login"}; // 不需要拦截的接口路径
        registry.addInterceptor(loginInterceptor).addPathPatterns(path).excludePathPatterns(excludePath);
    }
}
