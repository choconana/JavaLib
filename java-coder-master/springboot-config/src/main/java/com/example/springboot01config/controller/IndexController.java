package com.example.springboot01config.controller;

import com.example.springboot01config.bean.BlogProperties;
import com.example.springboot01config.bean.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private BlogProperties blogProperties;

    @Autowired
    private ConfigBean configBean;

    @RequestMapping("/")
    String index() {
        return configBean.getName() + "——" + configBean.getTitle();
    }
}