package com.sfy.jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {
    @RequestMapping("jspIndex")
    public String jspIndex(){
        return "jspIndex";
    }
}
