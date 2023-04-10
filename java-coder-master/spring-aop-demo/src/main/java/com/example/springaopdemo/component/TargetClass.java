package com.example.springaopdemo.component;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName TargetClass.java
 * @Description TODO
 * @createTime 2022年05月02日 20:30:00
 */
@Component
public class TargetClass {

    public String test(String value) {
        System.out.println("目标方法test被执行");
        if (!StringUtils.hasLength(value)) {
            throw new RuntimeException("value不能为空");
        }
        return value;
    }
}