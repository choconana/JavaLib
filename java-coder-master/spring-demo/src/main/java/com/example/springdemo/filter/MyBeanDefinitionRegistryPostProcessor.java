package com.example.springdemo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName MyBeanDefinitionRegistryPostProcessor.java
 * @Description TODO
 * @createTime 2022年05月01日 22:02:00
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private final Logger logger = LoggerFactory.getLogger(MyBeanDefinitionRegistryPostProcessor.class);

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        int beanDefinitionCount = registry.getBeanDefinitionCount();
        logger.info("Bean定义个数: " + beanDefinitionCount);
        // 添加一个新的Bean定义
        RootBeanDefinition definition = new RootBeanDefinition(Object.class);
        registry.registerBeanDefinition("hello", definition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}