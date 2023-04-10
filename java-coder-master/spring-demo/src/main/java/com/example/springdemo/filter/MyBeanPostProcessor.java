package com.example.springdemo.filter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName MyBeanPostProcessor.java
 * @Description Bean初始化回调
 * @createTime 2022年05月01日 20:44:00
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("springDemoApplication".equals(beanName)) {
            System.out.println("post processor before " + beanName + " initialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("springDemoApplication".equals(beanName)) {
            System.out.println("post processor after " + beanName + " initialization");
        }
        return bean;
    }

}
