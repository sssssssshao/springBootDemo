package com.sfy.service;

import com.sfy.annotation.MyService;

@MyService
public class OrderServiceImpl implements OrderService{

    public void addOrder() {
        System.out.println("依赖注入OrderServiceImpl");
    }
}
