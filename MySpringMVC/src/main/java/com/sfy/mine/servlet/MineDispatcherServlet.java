package com.sfy.mine.servlet;

import com.sfy.ext.utils.ClassUtil;
import com.sfy.mine.annotation.MineController;
import com.sfy.mine.annotation.MineRequestMapping;
import com.sfy.mine.annotation.MineResponseBody;
import com.sfy.mine.annotation.MineRestController;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MineDispatcherServlet extends HttpServlet {
    // key：类名  value：实例化对象
    ConcurrentHashMap<String, Object> controllerBeans = new ConcurrentHashMap<String, Object>();
    // key：url地址 value：实例化对象
    ConcurrentHashMap<String, Object> urlBeans = new ConcurrentHashMap<String, Object>();
    // key：url地址  value：方法名
    ConcurrentHashMap<String, String> urlMethods = new ConcurrentHashMap<String, String>();
    // 判断是否是rest的请求  key：url地址  value：方法
    ConcurrentHashMap<String, String> urlRestMethods = new ConcurrentHashMap<String, String>();
    @Override
    public void init() throws ServletException {
        findControllerAnnotation();
        handlerUrlMapping();
    }

    /**
     * 将所有带有@MineController的注解放到controllerBeans中
     */
    private void findControllerAnnotation(){
        try {
            // 1.获取包下的所有类
            // 2.判断所有的类中是否包含注解
            List<Class<?>> classes = ClassUtil.getClasses("com.sfy.mine.controller");
            for (Class<?> classInfo : classes) {
                MineController mineController = classInfo.getAnnotation(MineController.class);
                MineRestController mineRestController = classInfo.getAnnotation(MineRestController.class);
                if (mineController != null || mineRestController != null) {
                    controllerBeans.put(ClassUtil.toLowerCaseFirstOne(classInfo.getSimpleName()), ClassUtil.newInstance(classInfo));
                }
            }
        } catch (Exception e) {

        }
    }

    /**
     * 判断页面中是否
     */
    private void handlerUrlMapping(){
        for (Map.Entry<String, Object> entry : controllerBeans.entrySet()) {
            Object object = entry.getValue();
            MineRequestMapping mineRequestMapping = object.getClass().getAnnotation(MineRequestMapping.class);
            String[] rootUrls = new String[]{""};
            if (mineRequestMapping != null) {
                rootUrls = mineRequestMapping.value();
            }
            Boolean isRestController = false;
            MineRestController mineRestController = object.getClass().getAnnotation(MineRestController.class);
            if (mineRestController != null) {
                isRestController = true;
            }
            handlerUrlMappingForMethod(object, rootUrls, isRestController);
        }
    }

    /**
     * 遍历类中的方法
     */
    private void handlerUrlMappingForMethod(Object object, String[] rootUrls, Boolean isRestController){
        try {
            Method[] methods = object.getClass().getDeclaredMethods();
            for (Method method : methods) {
                MineRequestMapping mineRequestMapping = method.getAnnotation(MineRequestMapping.class);
                if (mineRequestMapping != null) {
                    String[] baseUrls = mineRequestMapping.value();
                    for (String rootUrl : rootUrls) {
                        for (String baseUrl : baseUrls) {
                            String url = rootUrl + baseUrl;
                            urlBeans.put(url, ClassUtil.newInstance(object.getClass()));
                            handlerUrlMappingForRestMethod(url, method, isRestController);
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    private void handlerUrlMappingForRestMethod(String url, Method method, Boolean isRestController){
        MineResponseBody mineResponseBody = method.getAnnotation(MineResponseBody.class);
        if (mineResponseBody != null || isRestController) {
            urlRestMethods.put(url, method.getName());
        } else {
            urlMethods.put(url, method.getName());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if (StringUtils.isNotBlank(url)) {
            Object object = urlBeans.get(url);
            if (object != null) {
                if (urlMethods.containsKey(url)) {
                    String result = invokeMethod(urlMethods.get(url), object);
                    if (StringUtils.isNotBlank(result)) {
                        req.getRequestDispatcher("/WEB-INF/views/" + result + ".jsp").forward(req, resp);
                        return;
                    }
                } else if (urlRestMethods.containsKey(url)) {
                    String result = invokeMethod(urlRestMethods.get(url), object);
                    if (StringUtils.isNotBlank(result)) {
                        resp.getWriter().println(result);
                        return;
                    }
                }
            }
        }
        resp.getWriter().println("not found page");
    }

    private String invokeMethod(String methodName, Object object){
        if (StringUtils.isNotBlank(methodName)) {
            try {
                String result = (String) object.getClass().getMethod(methodName).invoke(object);
                return result;
            } catch (Exception e) {

            }
        }
        return null;
    }
}
