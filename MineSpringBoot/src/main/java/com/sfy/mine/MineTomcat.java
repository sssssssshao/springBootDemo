package com.sfy.mine;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

public class MineTomcat {
    private static int PORT = 8080;
    private static String CONTEXT_PATH = "/";
    private static String PATHNAME = "MineSpringBoot/src/main";// MySpringBoot/
    private static String CLASSPATHNAME = "target/classes";
    private static String WEBAPPPATH = "/WEB-INF/classes";

    public static void main(String[] args) throws Exception{
        // 使用Java内置Tomcat运行SpringMVC框架
        // 原理：tomcat加载到springmvc注解启动方式，就会创建springmvc容器
        start();
    }

    public static void start() throws LifecycleException, ServletException {
        // 创建tomcat服务器
        Tomcat tomcatServer = new Tomcat();
        // 制定端口号
        tomcatServer.setPort(PORT);
        // 是否设置自动部署
//        tomcatServer.getHost().setAutoDeploy(false);
        // 读取项目路径 加载静态资源
        StandardContext ctx = (StandardContext) tomcatServer.addWebapp(CONTEXT_PATH, new File(PATHNAME).getAbsolutePath());
        // 禁止重新载入
        ctx.setReloadable(false);
        // class文件读取地址
        File additionWebInfClasses = new File(CLASSPATHNAME);
        // 创建WebRoot
        WebResourceRoot resources = new StandardRoot(ctx);
        // tomcat内部读取Class执行
        resources.addPreResources(new DirResourceSet(resources, WEBAPPPATH, additionWebInfClasses.getAbsolutePath(), CONTEXT_PATH));
        tomcatServer.start();
        System.out.println("tomcat服务器启动成功");

        //异步进行接受请求
        tomcatServer.getServer().await();
    }
}
