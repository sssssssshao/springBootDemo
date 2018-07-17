package com.sfy.ext.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 根配置
 * 其他非springmvc的容器
 */
@Configuration
@ComponentScan("com.sfy")
public class RootConfig {

}
