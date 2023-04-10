package com.example.springdemo.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName Bird.java
 * @Description TODO
 * @createTime 2022年05月01日 20:32:00
 */
public class Bird implements InitializingBean, DisposableBean {

    public Bird() {
        System.out.println("调用无参构造器创建Bird");
    }

    @Override
    public void destroy() {
        System.out.println("销毁Bird");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("初始化Bird");
    }
}