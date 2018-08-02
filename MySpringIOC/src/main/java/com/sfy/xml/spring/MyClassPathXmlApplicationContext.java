package com.sfy.xml.spring;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

// 自定义spring容器框架xml方式实现
public class MyClassPathXmlApplicationContext {
    // xml读取路径地址
    private String xmlPath;

    public MyClassPathXmlApplicationContext(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public Object getBean(String beanId)throws Exception{
        if (StringUtils.isEmpty(beanId)){
            throw new Exception("beanId不能为空");
        }
        // 1.解析xml文件,获取所有bean节点信息
        List<Element> readerXML = readerXML();
        if (readerXML == null || readerXML.isEmpty()) {
            throw new Exception("配置文件中，没有bean信息");
        }
        // 2.使用方法参数beanid查找配置文件中bean节点的id信息是否一致
        String className = findByElementClass(readerXML, beanId);
        // 3.获取class信息地址，使用反射机制初始化.
        if (StringUtils.isEmpty(className)){
            throw new Exception("该bean对象没有配置class地址");
        }
        // 3.使用反射机制初始化对象
        Object newInstance = newInstance(className);
        return newInstance;
    }

    // 2.使用方法参数beanid查找配置文件中bean节点的id信息是否一致.
    // 返回class地址
    public String findByElementClass(List<Element> readerXML, String beanId) {
        for (Element element : readerXML) {
            // 获取属性信息
            String xmlBeanId = element.attributeValue("id");
            if (StringUtils.isEmpty(xmlBeanId)) {
                continue;
            }
            // 如果一致
            if (xmlBeanId.equals(beanId)) {
                String xmlClass = element.attributeValue("class");
                return xmlClass;
            }
        }
        return null;
    }

    // 初始化对象
    public Object newInstance(String className) throws Exception{
        Class<?> classInfo = Class.forName(className);
        return classInfo.getDeclaredConstructor().newInstance();
    }

    // 解析xml文件
    public List<Element> readerXML() throws Exception{
        SAXReader saxReader = new SAXReader();
        // 读取xml文件
        // 1.解析xml文件信息
        Document document = saxReader.read(getResourceAsStream(this.xmlPath));
        // 2.读取根节点
        Element rootElement = document.getRootElement();
        // 3.读取根节点下所有的子节点
        List<Element> elements = rootElement.elements();
        return elements;
    }

    public InputStream getResourceAsStream(String xmlPath){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(xmlPath);
        return inputStream;
    }
    public static void main(String[] args){
        // 1.解析xml文件
        // 2.使用方法参数beanid查找配置文件中bean节点的id信息是否一致
        // 3.获取class信息地址，使用反射机制初始化

    }
}
