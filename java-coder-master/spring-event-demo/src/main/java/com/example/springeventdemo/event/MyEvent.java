package com.example.springeventdemo.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName MyEvent.java
 * @Description 事件
 * @createTime 2022年05月03日 19:58:00
 */
public class MyEvent extends ApplicationEvent {

    public boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public MyEvent(Object source) {
        super(source);
    }
}