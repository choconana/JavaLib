package com.example.springdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName User.java
 * @Description TODO
 * @createTime 2022年05月01日 16:44:00
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class User {
    private String name;
    private Integer age;
}