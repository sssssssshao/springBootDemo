package com.sfy.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * springmvc 配置信息
 * @EnableWebMvc 开始springmvc功能
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.sfy.controller"})
public class WebConfig extends WebMvcConfigurerAdapter {

    // 需要配置视图转换器


}
