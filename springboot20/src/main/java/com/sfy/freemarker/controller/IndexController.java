package com.sfy.freemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
public class IndexController {
    @RequestMapping("/ftlIndex")
    public String index(Map<String, String> map){
        map.put("name", "sfy");
        map.put("age", "12");
        map.put("sex", "0");
        return "ftlIndex";
    }
}
