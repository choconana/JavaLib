package com.example.springdemo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName MyBeanFactoryPostProcessor.java
 * @Description 测试 BeanFactoryPostProcessor
 * @createTime 2022年05月01日 21:51:00
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(MyBeanFactoryPostProcessor.class);

    public MyBeanFactoryPostProcessor() {
        logger.info("实例化MyBeanFactoryPostProcessor Bean");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        int beanDefinitionCount = beanFactory.getBeanDefinitionCount();
        logger.info("Bean定义个数: " + beanDefinitionCount);
    }

    @Component
    static class TestBean {
        public TestBean() {
            logger.info("实例化TestBean");
        }
    }
}