package com.example.springeventdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName AsyncEventConfigure.java
 * @Description 事件整体异步配置文件
 * @createTime 2022年05月04日 21:51:00
 */
@Configuration
public class AsyncEventConfigure {

    @Bean(name = AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }
}