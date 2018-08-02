package com.sfy.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    public UserServiceImpl() {
        System.out.println("无参构造函数初始化。。。反射技术");
    }

    public String getUser() {
        return "UserServiceImpl";
    }

}
