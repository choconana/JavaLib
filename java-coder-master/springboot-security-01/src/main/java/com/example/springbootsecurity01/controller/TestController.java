package com.example.springbootsecurity01.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello spring security";
    }

    @GetMapping("/index")
    public Object index() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("session/invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String sessionInvalid() {
        return "session已失效，请重新认证";
    }

    @GetMapping("/signout/success")
    public String signout() {
        return "退出成功，请重新登录";
    }

}