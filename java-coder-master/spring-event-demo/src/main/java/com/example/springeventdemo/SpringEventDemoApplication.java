package com.example.springeventdemo;

import com.example.springeventdemo.event.MyEventPublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

//@EnableAsync
@SpringBootApplication
public class SpringEventDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringEventDemoApplication.class, args);
        MyEventPublisher publisher = context.getBean(MyEventPublisher.class);
        publisher.publishEvent();
    }

}
