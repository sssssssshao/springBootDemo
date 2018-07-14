package com.sfy;

import com.sfy.config.JTADBConfig1;
import com.sfy.config.JTADBConfig2;
import com.sfy.datasource.DataSource1Config;
import com.sfy.datasource.DataSource2Config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 1.mybatis启动方式可以在mapper层不需要加mapper注解，但是一定要在启动类的时候加上@MapperScan
 * 2.mybatis在mybatis接口上@Mapper注入mybatis容器，就不需要加@MapperScan
 * 3.springboot2.0之后 spring5.0
 * */
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.sfy.controller","com.sfy.order.controller"})
@SpringBootApplication
@EnableAsync // 开启异步调用
//@MapperScan(basePackages = {"com.sfy.mapper", "com.sfy.test01.mapper", "com.sfy.test02.mapper"})
//@EnableConfigurationProperties(value = {JTADBConfig1.class, JTADBConfig2.class})
public class App {
    /**
     * 1.实现注解版本多数据源
     */
    public static void main(String[] args) {
        // 整个程序入口,启动SpringBoot项目 创建内置Tomcat服务器,使用tomcat加载springmvc 注解启动类
        // @ComponentScan 确定:如果扫包的比较多的话,写起来很麻烦
        // @SpringBootApplication 等于@EnableAutoConfiguration+@ComponentScan同级包和当前包com.sfy.controller
        SpringApplication.run(App.class, args);
    }
}
