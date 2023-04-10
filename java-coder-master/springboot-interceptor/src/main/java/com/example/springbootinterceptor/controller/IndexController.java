package com.example.springbootinterceptor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/user/account")
    public String userAccount() {
        return "/user/account";
    }

    @RequestMapping("/user/login")
    public String userLogin() {
        return "/user/login";
    }

    @RequestMapping("/user/health")
    public String health() {
        return "/user/health";
    }
}
