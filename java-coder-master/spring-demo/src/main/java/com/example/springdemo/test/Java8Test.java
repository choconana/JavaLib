package com.example.springdemo.test;

import com.example.springdemo.entity.User;

import java.util.Optional;

public class Java8Test {

    public static void main(String[] args) {
        Integer a = null;
        System.out.println(Optional.ofNullable(a).orElse(1));

        User user = new User();
        user.setName("name");

        System.out.println(Optional.ofNullable(user).map(User::getAge).orElse(123));
    }
}
