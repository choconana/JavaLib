package com.example.springdemo.entity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName Fish.java
 * @Description TODO
 * @createTime 2022年05月01日 20:41:00
 */
public class Fish {
    public Fish() {
        System.out.println("调用无参构造器创建Fish");
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化Fish");
    }

    @PreDestroy
    public void destory() {
        System.out.println("销毁Fish");
    }
}