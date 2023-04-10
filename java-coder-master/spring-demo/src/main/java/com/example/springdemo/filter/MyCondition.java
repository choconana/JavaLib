package com.example.springdemo.filter;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName MyCondition.java
 * @Description 条件判断
 * @createTime 2022年05月01日 17:42:00
 */
public class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String osName = context.getEnvironment().getProperty("os.name");
        return osName != null && osName.contains("Windows");
    }
}