package com.sfy;

import com.sfy.annotation.MyClassPathAnnotationApplicationContext;
import com.sfy.service.UserService;

public class AnnotationTest {

    public static void main(String[] args) throws Exception{
        MyClassPathAnnotationApplicationContext app = new MyClassPathAnnotationApplicationContext("com.sfy.service");
        UserService myUserService = (UserService) app.getBean("myUserServiceImpl");
        System.out.println(myUserService.getUser());

    }
}
