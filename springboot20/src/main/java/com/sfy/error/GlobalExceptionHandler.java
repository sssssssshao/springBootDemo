package com.sfy.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.捕获返回json格式
 * 2.捕获返回页面
 */
@ControllerAdvice(basePackages = "com.sfy")
public class GlobalExceptionHandler{

    // @ResponseBody 返回json格式
    // modeAndView 返回页面
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String errorResult(HttpServletRequest req, HttpServletResponse rsp, Exception e) throws Exception {
        // 实际开发中, 怎么将错误记录在日志中
        Map<String, Object> errorResultMap = new HashMap<>();
        errorResultMap.put("errorCode", "500");
        errorResultMap.put("errorMsg", "全局系统错误");
        return e.getMessage();
    }
}
