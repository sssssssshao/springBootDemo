package com.sfy.ext.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping(value = "/index", produces = "text/html;charset=UTF-8")
    public String index(){
        return "纯手写SpringBoot已完成!";
    }
}
