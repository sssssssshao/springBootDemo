package com.sfy.ext.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 加载springmvc--dispatcherServlert
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 加载根配置信息 spring核心
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * springmvc加载配置信息
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     *  springmvc 拦截url映射 拦截所有请求
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};//拦截所有请求
    }
}
