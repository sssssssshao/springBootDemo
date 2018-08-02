package com.sfy.service;

import com.sfy.annotation.MyResource;
import com.sfy.annotation.MyService;

import javax.annotation.Resource;

@MyService
public class MyUserServiceImpl implements  UserService{

    @MyResource
    private OrderService orderServiceImpl;

    public String getUser() {
        orderServiceImpl.addOrder();
        return "MyUserServiceImpl 注解版本IOC";
    }
}
