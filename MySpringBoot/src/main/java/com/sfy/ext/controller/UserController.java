package com.sfy.ext.controller;

import com.sfy.ext.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 跳转页面
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/pageIndex")
    public String pageIndex(){
        return "pageIndex";
    }

    @ResponseBody
    @RequestMapping(value = "/service", produces = "text/html;charset=UTF-8")
    public String service(){
        return userService.index();
    }
}
