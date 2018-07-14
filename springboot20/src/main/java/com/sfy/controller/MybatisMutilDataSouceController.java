package com.sfy.controller;

//import com.sfy.test01.service.UserTest01Service;
//import com.sfy.test02.service.UserTest02Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 多数据源测试
 */
//@RestController
//public class MybatisMutilDataSouceController {
//    @Autowired
//    private UserTest01Service userTest01Service;
//    @Autowired
//    private UserTest02Service userTest02Service;
//
//    @RequestMapping("insertUserTest01")
//    public int insertUserTest01(String name, int age) {
//        return userTest01Service.insert(name, age);
//    }
//
//    @RequestMapping("insertUserTest02")
//    public int insertUserTest02(String name, int age) {
//        return userTest02Service.insert(name, age);
//    }
//
//    @RequestMapping("insertAll")
//    public int insertTest0102(String name, int age){
//        return userTest01Service.insertUserTest(name, age);
//    }
//}
