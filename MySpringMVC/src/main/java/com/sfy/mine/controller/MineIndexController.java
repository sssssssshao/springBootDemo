package com.sfy.mine.controller;

import com.sfy.mine.annotation.MineController;
import com.sfy.mine.annotation.MineRequestMapping;
import com.sfy.mine.annotation.MineResponseBody;

@MineController
@MineRequestMapping("/mine")
public class MineIndexController {
    @MineRequestMapping("/index")
    public String index(){
        return "index";
    }
    @MineRequestMapping("/showResult")
    @MineResponseBody
    public String showResult(){
        return "showResult";
    }
}
