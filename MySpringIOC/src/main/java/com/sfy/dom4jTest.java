package com.sfy;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class dom4jTest {
    public static void main(String[] args) throws DocumentException{
        new dom4jTest().test001();
    }

    public void test001() throws DocumentException{
        SAXReader saxReader = new SAXReader();
        // 读取XML文件
        Document document = saxReader.read(getResourceAsStream("student.xml"));
        // 读取根节点
        Element rootElement = document.getRootElement();
        getNodes(rootElement);
    }

    public void getNodes(Element rootElement){
        System.out.println("获取节点名称:" + rootElement.getName());
        // 获取属性方法
        List<Attribute> attributeList = rootElement.attributes();
        for (Attribute attribute : attributeList) {
            System.out.println(attribute.getName() + "----" + attribute.getText());
        }
        // 获取属性value
        String textTrim = rootElement.getTextTrim();
        if (StringUtils.isEmpty(textTrim)) {
            System.out.println("textTrim:" + textTrim);
        }

        // 使用迭代器获取子节点信息
        Iterator<Element> elementIterator = rootElement.elementIterator();
        while(elementIterator.hasNext()) {
            Element next = elementIterator.next();
            getNodes(next);
        }

    }

    public InputStream getResourceAsStream(String xmlPath){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(xmlPath);
        return inputStream;
    }
}
