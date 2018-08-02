package com.sfy;

import com.sfy.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test001 {

    public static void main(String[] args){
        // SpinrgIOC XML版本使用dom4j + 反射机制
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("springIOC.xml");
        UserService userService = (UserService)app.getBean("userService");
        System.out.println(userService.getUser());
    }
}
