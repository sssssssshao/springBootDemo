package com.sfy.controller;

import com.github.pagehelper.PageInfo;
import com.sfy.entity.User;
import com.sfy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("insert")
    public int insert(String name, int age){
        return userService.insert(name, age);
    }

    @RequestMapping("list")
    public PageInfo<User> list(int page, int pageSize) {
        return userService.findUserList(page, pageSize);
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
