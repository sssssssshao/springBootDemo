package com.sfy.annotation;

import com.sfy.annotation.MyService;
import com.sfy.utils.ClassUtil;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 手写 SpringIOC 注解版本
public class MyClassPathAnnotationApplicationContext {
    // 扫包的范围
    private String packageName;

    // springbean容器
    private ConcurrentHashMap<String, Object> beans = null;

    public MyClassPathAnnotationApplicationContext(String packageName) throws Exception {
        if (StringUtils.isEmpty(packageName)) {
            throw new Exception("包名不能为空");
        }
        this.packageName = packageName;
        beans = new ConcurrentHashMap<String, Object>();
        initBeans();
        initEntryField();
    }

    // 初始化属性
    private void initEntryField() throws Exception{
        // 1.遍历所有的 bean 容器对象
        for (Map.Entry<String, Object> entry: beans.entrySet()) {
            // 2.判断属性上面是否有加注解
            Object bean = entry.getValue();
            attriAssign(bean);
        }
    }


    public Object getBean(String beanId) throws Exception {
        if (StringUtils.isEmpty(beanId)) {
            throw new Exception("beanId参数不能为空");
        }
        // 1.从 spring 容器获取 bean
        Object object = beans.get(beanId);
//        if (object == null) {
//            throw new Exception("没有找到该 bean 对象");
//        }
        // 2.使用反射机制初始化对象
        return object;
    }

    // 初始化对象
    public Object newInstance(Class<?> classInfo) throws Exception {
        return classInfo.newInstance();
    }

    // 初始化对象
    public void initBeans() throws Exception{
        // 1.使用java的反射机制扫包，获取当前包下的所有的类
        List<Class<?>> classes = ClassUtil.getClasses(packageName);
        // 2.判断类上是否存在注入bean的注解
        ConcurrentHashMap<String, Object> classExisAnnotation = findClassExisAnnotation(classes);
        if (classExisAnnotation == null || classExisAnnotation.isEmpty()) {
            throw new Exception("该包下没有任何类加上注解");
        }

        // 3.使用java的反射机制进行初始化
    }

    // 2.判断类上是否存在注入bean的注解
    public ConcurrentHashMap<String, Object> findClassExisAnnotation(List<Class<?>> classes) throws Exception {
        for (Class<?> classInfo : classes) {
            // 判断类上是否有注解
            MyService annotation = classInfo.getAnnotation(MyService.class);
            if (annotation != null) {
                // beanId 类型小写
                // 获取当前类名
                String className = classInfo.getSimpleName();
                // 将当前类名首字母小写
                String beanId = toLowerCaseFirstOne(className);
                Object newInstance = newInstance(classInfo);
                beans.put(beanId , newInstance);
                continue;
            }
        }
        return beans;
    }

    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    // 依赖注入注解原理
    public void attriAssign(Object object) throws Exception{
        // 依赖注入注解原理
        // 1.使用反射机制，获取当前类的所有属性
        Class<? extends Object> classInfo = object.getClass();
        Field[] declaredFields = classInfo.getDeclaredFields();
        // 2.判断当前类属性是否存在注解
        for (Field field : declaredFields) {
            MyResource myResource = field.getAnnotation(MyResource.class);
            if (myResource != null) {
                // 获取属性名称
                String beanId = field.getName();
                Object bean = beans.get(beanId);
                if (bean != null) {
                    // 3.默认使用属性名称，查找 bean 容器对象
                    field.setAccessible(true);// 允许访问私有属性
                    field.set(object, bean);
                }
            }
        }

    }

    public static void main(String[] args){

    }
}
