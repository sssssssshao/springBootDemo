package com.sfy;

import com.sfy.service.UserService;
import com.sfy.xml.spring.MyClassPathXmlApplicationContext;

public class xmlTest {

    public static void main(String[] args) throws Exception{
        MyClassPathXmlApplicationContext myClassPathXmlApplicationContext = new MyClassPathXmlApplicationContext("springIOC.xml");
        UserService userService = (UserService) myClassPathXmlApplicationContext.getBean("userService");
        System.out.println(userService + "-----" + userService.getUser());
    }
}
