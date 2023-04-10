package com.example.springeventdemo.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName MyEventPublisher.java
 * @Description 事件发布
 * @createTime 2022年05月03日 19:59:00
 */
@Component
public class MyEventPublisher implements ApplicationEventPublisherAware, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private ApplicationEventPublisher applicationEventPublisher;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent() {
        logger.info("开始发布自定义事件MyEvent");
        MyEvent myEvent = new MyEvent(applicationContext);
        myEvent.setFlag(false); // 设置为false
        applicationEventPublisher.publishEvent(myEvent);
        logger.info("发布自定义事件MyEvent结束");
    }

}