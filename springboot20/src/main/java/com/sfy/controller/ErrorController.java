package com.sfy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局捕获异常
 */
@RestController
public class ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    // 分布式日志收集系统
    // 全局捕获异常 使用AOP技术,采用异常通知
    // 如果每个方法都看会发生异常,每个方法都得写上try
    @RequestMapping("/getUser")
    public String getUser(int i ){
        logger.info("日志成功");
        int j  = 1 / i;
        return "success" + j;
    }

    @RequestMapping("/getMember")
    public String getMember(String name, int age){
//        logger.info("name:" + name + ";age:" + age);
        return name;
    }
}
