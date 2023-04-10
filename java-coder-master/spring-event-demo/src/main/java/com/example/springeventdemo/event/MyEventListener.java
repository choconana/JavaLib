package com.example.springeventdemo.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName MyEventListener.java
 * @Description 编程监听
 * @createTime 2022年05月03日 22:33:00
 */
@Component
public class MyEventListener implements ApplicationListener<MyEvent>, Ordered {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(MyEvent event) {
        logger.info("编程方式收到自定义事件MyEvent，我的优先级较高");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}