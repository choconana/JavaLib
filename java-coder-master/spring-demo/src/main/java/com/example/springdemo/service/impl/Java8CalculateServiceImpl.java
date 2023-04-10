package com.example.springdemo.service.impl;

import com.example.springdemo.service.CalculateService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName Java8CalculateServiceImpl.java
 * @Description TODO
 * @createTime 2022年05月01日 17:47:00
 */
@Service
@Profile("java8")
public class Java8CalculateServiceImpl implements CalculateService {
    @Override
    public Integer sum(Integer... value) {
        System.out.println("Java 8环境下执行");
        return Arrays.stream(value).reduce(0, Integer::sum);
    }
}