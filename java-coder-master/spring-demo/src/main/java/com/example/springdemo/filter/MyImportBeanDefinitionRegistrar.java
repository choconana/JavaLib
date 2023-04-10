package com.example.springdemo.filter;

import com.example.springdemo.entity.Strawberry;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName MyImportBeanDefinitionRegistrar.java
 * @Description 声明并注册组件
 * @createTime 2022年05月01日 18:05:00
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        final String beanName = "strawberry";
        boolean contain = registry.containsBeanDefinition(beanName);
        if (!contain) {
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Strawberry.class);
            registry.registerBeanDefinition(beanName, rootBeanDefinition);
        }
    }
}