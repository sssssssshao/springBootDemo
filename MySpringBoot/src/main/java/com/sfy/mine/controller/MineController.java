package com.sfy.mine.controller;

import com.sfy.mine.service.MineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MineController {
    @Autowired
    private MineService mineService;

    @RequestMapping("/mine")
    public String mine(){
        return "pageIndex";
    }

    @RequestMapping(value = "/mineService", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String mineService(){
        return mineService.getResult();
    }
}
