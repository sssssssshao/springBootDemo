package com.sfy.tomcat;

import com.sfy.servlet.IndexServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

/**
 * 使用Java语言创建Tomcat服务器
 * Tomcat底层执行程序 最终servlet
 * SpringMVC底层使用servlet包装
 */
public class Test001 {
    private static int PORT = 8080;
    private static String CONTEXT_PATH = "/sfy";
    private static String SERVLET_NAME = "indexServlet";

    public static void main(String[] args) throws LifecycleException, InterruptedException {
        // 创建tomcat服务器
        Tomcat tomcatServer = new Tomcat();
        // 制定端口号
        tomcatServer.setPort(PORT);
        // 是否设置自动部署
        tomcatServer.getHost().setAutoDeploy(false);
        // 创建上下文
        StandardContext standardContext = new StandardContext();
        standardContext.setPath(CONTEXT_PATH);
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
