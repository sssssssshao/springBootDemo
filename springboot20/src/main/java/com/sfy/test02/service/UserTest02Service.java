package com.sfy.test02.service;

import com.sfy.test02.mapper.UserTest02Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserTest02Service {

    @Autowired
    private UserTest02Mapper userMapper;

    @Transactional(transactionManager = "test2TransactionManager")
    public int insert(String name, int age){
        log.info("----------------insert----------------");
        int insertCount = userMapper.insert(name, age);
        int i = 1 / age;
        // 怎么样验证事务开启成功？
        return insertCount;
    }

}
