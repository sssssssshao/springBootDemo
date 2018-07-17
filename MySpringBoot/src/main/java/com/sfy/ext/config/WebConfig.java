package com.sfy.ext.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * springmvc 配置信息
 * @EnableWebMvc 开始springmvc功能
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.sfy.ext.controller"})
public class WebConfig extends WebMvcConfigurerAdapter {

    // springboot整合jsp 最好是war
    // 需要配置视图转换器
    // 创建SpringMVC视图解析器
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/webapp/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        // 可以在JSP页面中通过${}访问beans
        viewResolver.setExposeContextBeansAsAttributes(true);
        return viewResolver;
    }

}
