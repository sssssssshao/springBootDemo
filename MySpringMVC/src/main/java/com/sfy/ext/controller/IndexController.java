package com.sfy.ext.controller;

import com.sfy.ext.annotation.ExtController;
import com.sfy.ext.annotation.ExtRequestMapping;

@ExtController
@ExtRequestMapping("/ext")
public class IndexController {
    @ExtRequestMapping("/index")
    public String index(){
        return "index";
    }
}
