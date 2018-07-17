package com.sfy.mine.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 根配置 其他非spring容器
 */
@Configuration
@ComponentScan("com.sfy.mine")
public class RootConfig {
}
