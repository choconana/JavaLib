package com.example.springdemo.filter;

import com.example.springdemo.entity.Cherry;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName CherryFactoryBean.java
 * @Description 通过FactoryBean创建对应并注册
 * @createTime 2022年05月01日 18:12:00
 */
public class CherryFactoryBean implements FactoryBean<Cherry> {

    @Override
    public Cherry getObject() {
        return new Cherry();
    }

    @Override
    public Class<?> getObjectType() {
        return Cherry.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}