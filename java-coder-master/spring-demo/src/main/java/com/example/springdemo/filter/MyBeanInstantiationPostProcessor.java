package com.example.springdemo.filter;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName MyBeanInstantiationPostProcessor.java
 * @Description Bean实例化调用
 * @createTime 2022年05月01日 21:04:00
 */
public class MyBeanInstantiationPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if ("springDemoApplication".equals(beanName)) {
            System.out.println("post process before " + beanName + " instantiation");
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if ("springDemoApplication".equals(beanName)) {
            System.out.println("post process after " + beanName + " instantiation");
        }
        // 为true则调用postProcessProperties
        return false;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if ("springDemoApplication".equals(beanName)) {
            System.out.println("post process " + beanName + " properties");
        }
        return pvs;
    }
}
