package com.sfy.member.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    // @RestController注解表示该类中的所有方法返回json格式   等同于@Controller+@ResponseBody
    // @RestController 是springboot提供吗?为微服务提供json格式
    // springboot启动原理:springmvc注解方式启动  内置http服务器(默认是Tomcat)
    // 1.@EnableAutoConfiguration 注解作用:扫包范围 默认在当前类里面
    // 2.启动 加上扫包范围
    @RequestMapping("/hello")
    public String memberIndex(){
        return "Hello ";
    }

}
