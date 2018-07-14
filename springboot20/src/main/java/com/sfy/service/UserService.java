package com.sfy.service;

import com.sfy.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService {

//    @Autowired
//    private UserMapper userMapper;
//
//    /**
//     * 如果不加@Transactional事务注解，那么springboot将会默认提交事务，如果已执行了insert方法，那么即使报错也会提交事务
//     */
//    @Transactional
//    public int insert(String name, int age){
//        log.info("----------------insert----------------");
//        int insertCount = userMapper.insert(name, age);
//        int i = 1 / age;
//        // 怎么样验证事务开启成功？
//        return insertCount;
//    }

}
