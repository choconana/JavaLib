package com.example.springdemo.service.impl;

import com.example.springdemo.service.CalculateService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName Java7CalculateServiceImpl.java
 * @Description TODO
 * @createTime 2022年05月01日 17:47:00
 */
@Service
@Profile("java7")
public class Java7CalculateServiceImpl implements CalculateService {
    @Override
    public Integer sum(Integer... value) {
        System.out.println("Java 7环境下执行");
        int result = 0;
        for (int i = 0; i <= value.length; i++) {
            result += i;
        }
        return result;
    }
}