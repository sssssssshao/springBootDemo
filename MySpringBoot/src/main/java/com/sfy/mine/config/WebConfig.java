package com.sfy.mine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * springmvc 配置中心
 * @EnableWebMvc 开启springmvc功能
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.sfy.mine.controller"})
public class WebConfig extends WebMvcConfigurerAdapter {

    // 配置视图转换器
    // 创建SpringMVC视图解析器
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/webapp/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        // 可以在页面上通过${}访问beans
        viewResolver.setExposeContextBeansAsAttributes(true);
        return viewResolver;
    }
}
