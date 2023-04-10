package com.example.springdemo.entity;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName UserBean.java
 * @Description TODO
 * @createTime 2022年05月01日 19:04:00
 */
public class UserBean {

    public UserBean() {
        System.out.println("调用无参构造器创建UserBean");
    }

    public void init() {
        System.out.println("初始化UserBean");
    }

    public void destory() {
        System.out.println("销毁UserBean");
    }

}
