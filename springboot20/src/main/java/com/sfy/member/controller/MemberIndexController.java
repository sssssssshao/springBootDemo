package com.sfy.member.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberIndexController {
    @RequestMapping("memberIndex")
    public String index(){
        return "index";
    }
}
