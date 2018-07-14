package com.sfy.test01.service;

import com.sfy.test01.mapper.UserTest01Mapper;
import com.sfy.test02.mapper.UserTest02Mapper;
import com.sfy.test02.service.UserTest02Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserTest01Service {

    @Autowired
    private UserTest01Mapper userTest01Mapper;
    @Autowired
    private UserTest02Mapper userTest02Mapper;

    @Transactional(transactionManager = "test1TransactionManager")
    public int insert(String name, int age){
        log.info("----------------insert----------------");
        int insertCount = userTest01Mapper.insert(name, age);
        int i = 1 / age;
        // 怎么样验证事务开启成功？
        return insertCount;
    }

    @Transactional
    public int insertUserTest(String name, int age){
        int test01 = userTest01Mapper.insert(name, age);
        int test02 = userTest02Mapper.insert(name, age);
        int i = 1 / age;
        return test01+test02;
    }

}
