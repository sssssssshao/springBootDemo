package com.sfy.ext.controller;

import com.sfy.ext.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AsyncController {
    @Autowired
    private AsyncService asyncService;
    // 在初始化的时候
    @Value("${name}")
    private String name;
    @Value("${http_url}")
    private String httpUrl;

    @RequestMapping("/addMemberAndEmail")
    public String addMemberAndEmail() throws InterruptedException{
        log.info("1");
        Thread.sleep(500);
        String result = asyncService.addMemberAndEmail();
        log.info("4");
        return result;
    }

    @RequestMapping("/getName")
    public String getName() {
        return name;
    }

    @RequestMapping("/httpUrl")
    public String getHttpUrl() {
        return httpUrl;
    }
}
