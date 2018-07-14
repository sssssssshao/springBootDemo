package com.sfy.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfy.entity.User;
import com.sfy.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 如果不加@Transactional事务注解，那么springboot将会默认提交事务，如果已执行了insert方法，那么即使报错也会提交事务
     */
    @Transactional
    public int insert(String name, int age){
        log.info("----------------insert----------------");
        int insertCount = userMapper.insert(name, age);
        int i = 1 / age;
        // 怎么样验证事务开启成功？
        return insertCount;
    }

    public PageInfo<User> findUserList(int page, int pageSize) {
        PageHelper.startPage(page, pageSize); //  底层实现原理采用改写语句
        List<User> users = userMapper.findUserList();
        // 返回给客户端
        PageInfo<User> pageInfo = new PageInfo<User>(users);
        return pageInfo;
    }
}
