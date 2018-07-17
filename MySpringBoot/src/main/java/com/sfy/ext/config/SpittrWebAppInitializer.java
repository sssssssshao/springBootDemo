package com.sfy.ext.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 加载springmvc--dispatcherServlert
 * 因为同一个项目存在多个Tomcat时，为了防止读取出错，需要注释掉 extends AbstractAnnotationConfigDispatcherServletInitializer
 * 防止初始化出错，导致无法启动tomcat
 */
public class SpittrWebAppInitializer
//        extends AbstractAnnotationConfigDispatcherServletInitializer
{
    /**
     * 加载根配置信息 spring核心
     */
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * springmvc加载配置信息
     */
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     *  springmvc 拦截url映射 拦截所有请求
     */
    protected String[] getServletMappings() {
        return new String[]{"/"};//拦截所有请求
    }
}
