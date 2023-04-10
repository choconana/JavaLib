package com.example.springtransactiondemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName User.java
 * @Description TODO
 * @createTime 2022年05月03日 17:16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private String userId;

    private String username;

    private String age;

}