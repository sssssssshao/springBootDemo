package com.sfy;

import com.sfy.servlet.IndexServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

public class AppTomcat {

    public static void main(String[] args){
        // 使用Java内置Tomcat运行SpringMVC框架   原理：tomcat加载到springmvc注解启动方式，就会创建springmvc容器
    }
    public static void start() throws LifecycleException, InterruptedException {
        // 创建tomcat服务器
        Tomcat tomcatServer = new Tomcat();
        // 制定端口号
        tomcatServer.setPort(8080);
        // 是否设置自动部署
        tomcatServer.getHost().setAutoDeploy(false);
        // 创建上下文
        StandardContext standardContext = new StandardContext();
        standardContext.setPath("");
        // 监听上下文
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());
        // Tomcat容器添加standarContext
        tomcatServer.getHost().addChild(standardContext);

        // 创建servlet
        tomcatServer.addServlet(CONTEXT_PATH, SERVLET_NAME, new IndexServlet());
        // servleturl映射
        standardContext.addServletMappingDecoded("/index", SERVLET_NAME);

        tomcatServer.start();
        System.out.println("tomcat服务器启动成功");

        //异步进行接受请求
        tomcatServer.getServer().await();
    }
}
