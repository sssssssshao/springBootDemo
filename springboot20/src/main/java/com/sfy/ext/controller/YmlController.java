package com.sfy.ext.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YmlController {

    @Value("${info.sfy.name}")
    private String name;

    @Value("${info.sfy.home.phone}")
    private String phone;

    @RequestMapping("ymlName")
    public String ymlName(){
        return name + ":" + phone;
    }
}
